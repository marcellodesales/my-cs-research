<?

Class File
{
        var $ERROR = "";
        var $BUFFER = -1;
        var $STATCACHE = array();
        var $TEMPDIR = '/tmp';
        var $REALUID = -1;
        var $REALGID = -1;
        var $fileCount = 0;


        function File ()
        {
                global $php_errormsg;
                return;
        }

        function clear_cache()
        {
                unset($this->STATCACHE);
                $this->STATCACHE = array();
                return true;
        }

        function is_sane($fileName = "", $must_exist = 0, $noSymLinks = 0, $noDirs = 0)
        {
                $exists = false;

                if(empty($fileName)) {        return false; }
                if($must_exist != 0)
                {
                        if(!file_exists($fileName))
                        {
                                $this->ERROR = "is_sane: [$fileName] does not exist";
                                return false;
                        }
                        $exists = true;
                }
                if($exists)
                {
                        if(!is_readable($fileName))
                        {
                                $this->ERROR = "is_sane: [$fileName] not readable";
                                return false;
                        }

                        if($noDirs != 0)
                        {
                                if(is_dir($fileName))
                                {
                                        $this->ERROR = "is_sane: [$fileName] is a directory";
                                        return false;
                                }
                        }

                        if($noSymLinks != 0)
                        {
                                if(is_link($fileName))
                                {
                                        $this->ERROR = "is_sane: [$fileName] is a symlink";
                                        return false;
                                }
                        }

                } // end if exists

                return true;
        }


//        **************************************************************

        function read_file ($fileName = "" )
        {
                $contents = "";

                if(empty($fileName))
                {
                        $this->ERROR = "read_file: No file specified";
                        return false;
                }
                if(!$this->is_sane($fileName,1,0,1))
                {
                        // Preserve the is_sane() error msg
                        return false;
                }
                $fd = @fopen($fileName,"r");

                if( (!$fd) || (empty($fd)) )
                {
                        $this->ERROR = "read_file: File error: [$php_errormsg]";
                        return false;
                }

                $contents = fread($fd, filesize($fileName) );

                fclose($fd);

        return $contents;
        }

//        **************************************************************
//        Read a file via fgetss(), which strips all php/html
//        from the file.

        function strip_read ($fileName = "", $strip_cr = 0)
        {
                if(empty($fileName))
                {
                        $this->ERROR = "strip_read: No file specified";
                        return false;
                }
                if(!$this->is_sane($fileName,1,0,1))
                {
                        // Preserve the error
                        return false;
                }
                if($this->BUFFER > 0)
                {
                        $buffer = $this->BUFFER;
                } else {
                        $buffer = filesize($fileName);
                }

                $contents = "";

                $fd = @fopen($fileName,"r");

                if( (!$fd) || (empty($fd)) )
                {
                        $this->ERROR = "strip_read: File error: [$php_errormsg]";
                        return false;
                }
                while(!feof($fd))
                {
                        $contents .= fgetss($fd,$buffer);
                }
                fclose($fd);
        return $contents;
        }

//        **************************************************************
        function write_file ($fileName,$Data)
        {
                $tempDir = $this->TEMPDIR;
                $tempfile   = tempnam( $tempDir, "cdi" );

                if(!$this->is_sane($fileName,0,1,1))
                {
                        return false;
                }

                if (file_exists($fileName))
                {
                        if (!copy($fileName, $tempfile))
                        {
                                $this->ERROR = "write_file: cannot create backup file [$tempfile] :  [$php_errormsg]";
                                return false;
                        }
                }

                $fd = @fopen( $tempfile, "a" );

                if( (!$fd) or (empty($fd)) )
                {
                        $myerror = $php_errormsg;
                        unlink($tempfile);
                        $this->ERROR = "write_file: [$tempfile] access error [$myerror]";
                        return false;
                }

                fwrite($fd, $Data);

                fclose($fd);

                if (!copy($tempfile, $fileName))
                {
                        $myerror = $php_errormsg;   // Stash the error, see above
                        unlink($tempfile);
                        $this->ERROR = "write_file: Cannot copy file [$fileName] [$myerror]";
                        return false;
                }

                unlink($tempfile);

                if(file_exists($tempfile))
                {
                        // Not fatal but it should be noted
                        $this->ERROR = "write_file: Could not unlink [$tempfile] : [$php_errormsg]";
                }
                return true;
        }

//        **************************************************************
        function copy_file ($oldFile = "", $newFile = "")
        {
                if(empty($oldFile))
                {
                        $this->ERROR = "copy_file: oldFile not specified";
                        return false;
                }
                if(empty($newFile))
                {
                        $this->ERROR = "copy_file: newFile not specified";
                        return false;
                }
                if(!$this->is_sane($oldFile,1,0,1))
                {
                        // preserve the error
                        return false;
                }
                if(!$this->is_sane($newFile,0,1,1))
                {
                        // preserve it
                        return false;
                }

                if (! (@copy($oldFile, $newFile)))
                {
                        $this->ERROR = "copy_file: cannot copy file [$oldFile] [$php_errormsg]";
                        return false;
                }

                return true;
        }

//        **********************************************

        function get_files ($root_dir, $fileExt = 'ALL_FILES')
        {
                $fileList = array();

                if(!is_dir($root_dir))
                {
                        $this->ERROR = "get_files: Sorry, [$root_dir] is not a directory";
                        return false;
                }

                if(empty($fileExt))
                {
                        $this->ERROR = "get_files: No file extensions specified";
                        return false;
                }

                $open_dir = @opendir($root_dir);

                if( (!$open_dir) or (empty($open_dir)) )
                {
                        $this->ERROR = "get_files: Failed to open dir [$root_dir] : $php_errormsg";
                        return false;
                }

                while ( $file = readdir($open_dir))
                {
                        if( (!is_dir($file)) and (!empty($file)) )
                        {
                                if($fileExt == 'ALL_FILES')
                                {
                                        $fileList[$this->fileCount] = $file;
                                        $this->fileCount++;
                                }
                                else
                                {
                                        if(eregi(".\.($fileExt)$",$file))
                                        {
                                                $fileList[$this->fileCount] = $file;
                                                $this->fileCount++;
                                        }
                                }
                        }
                }

                closedir($open_dir);
                return $fileList;

        }        // end get_files

        function is_owner($fileName, $uid = "")
        {
                if(empty($uid))
                {
                        if($this->REALUID < 0)
                        {
                                $tempDir = $this->TEMPDIR;
                                $tempFile = tempnam($tempDir,"cdi");
                                if(!touch($tempFile))
                                {
                                        $this->ERROR = "is_owner: Unable to create [$tempFile]";
                                        return false;
                                }
                                $stats = stat($tempFile);
                                unlink($tempFile);
                                $uid = $stats[4];
                        }
                        else
                        {
                                $uid = $this->REALUID;
                        }
                }
                $fileStats = stat($fileName);
                if( (empty($fileStats)) or (!$fileStats) )
                {
                        $this->ERROR = "is_owner: Unable to stat [$fileName]";
                        return false;
                }

                $this->STATCACHE = $fileStats;

                $owner = $fileStats[4];
                if($owner == $uid)
                {
                        return true;
                }

                $this->ERROR = "is_owner: Owner [$owner] Uid [$uid] FAILED";
                return false;
        }

        function is_inGroup($fileName, $gid = "")
        {
                if(empty($gid))
                {
                        if($this->REALGID < 0)
                        {
                                $tempDir = $this->TEMPDIR;
                                $tempFile = tempnam($tempDir,"cdi");
                                if(!touch($tempFile))
                                {
                                        $this->ERROR = "is_inGroup: Unable to create [$tempFile]";
                                        return false;
                                }
                                $stats = stat($tempFile);
                                unlink($tempFile);
                                $gid = $stats[5];
                        }
                        else
                        {
                                $gid = $this->REALGID;
                        }
                }
                $fileStats = stat($fileName);
                if( (empty($fileStats)) or (!$fileStats) )
                {
                        $this->ERROR = "is_inGroup: Unable to stat [$fileName]";
                        return false;
                }

                $this->STATCACHE = $fileStats;

                $group = $fileStats[5];
                if($group == $gid)
                {
                        return true;
                }

                $this->ERROR = "is_inGroup: Group [$group] Gid [$gid] FAILED";
                return false;
        }

        function get_real_uid()
        {
                $tempDir = $this->TEMPDIR;
                $tempFile = tempnam($tempDir,"cdi");
                if(!touch($tempFile))
                {
                        $this->ERROR = "is_owner: Unable to create [$tempFile]";
                        return false;
                }
                $stats = stat($tempFile);
                unlink($tempFile);
                $uid = $stats[4];
                $gid = $stats[5];
                $this->REALUID = $uid;
                $this->REALGID = $gid;
                return $uid;
        }

        function get_real_gid()
        {
                $uid = $this->get_real_uid();
                if( (!$uid) or (empty($uid)) )
                {
                        return false;
                }
                return $this->REALGID;
                }

}        // end class File

?>