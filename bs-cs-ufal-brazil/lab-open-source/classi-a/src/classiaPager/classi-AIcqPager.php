<?

if (isset($submit)){
	$newMessage = "Interesse no anúncio \"$announceTitle\" no Classi-A.com.br\n";
	$newMessage.= $message;
	$email = "$announcerICQ@pager.icq.com";
	$rest = "From: $interestedName <$interestedEmail>";
	
	mail($email, $subject, $newMessage, $rest);
	
	include("sucessfullClassi-APagerSent.php");
} else {
	require("classi-AFormPager.php");
	
}

?>
