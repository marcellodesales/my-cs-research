/*
 * @created 23/09/2004 11:36:58
 * <a href=mailto:masj2@cin.ufpe.br>Marcello Alves de Sales Junior</a>
 * Center of Informatics (CIn)-UFPE
 * Software Test Program
 * CIn / UFPE / Brazil
 * */
 
package br.ufpe.cin.stp.ptfaddon.model.log.interpreter;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import br.ufpe.cin.stp.global.DateUtils;
import br.ufpe.cin.stp.ptfaddon.model.log.Instruction;

/**
 * @created 23/09/2004 11:36:58
 * @author Marcello Sales Jr. <a href='masj2@cin.ufpe.br'>masj2@cin.ufpe.br</a>
 * @version 1.0
 */
public abstract class AbstractInstructionExpression implements InstructionExpressionIF{
    
    /**
     * <code>line</code> is the instruction line
     */
    private String line;
    
    private String instructionToken;
    private String dateTimeToken;
    private String content;
    
    private Instruction instruction;
    
    /**
     * Creates an abstract instruction expression with a given line
     * @created 23/09/2004 12:02:12
     * @param line
     */
    public AbstractInstructionExpression(String line) throws LogInterpreterException{
        this.line = line;
        if (!this.containsToken(line))
            throw new LogTokenNotFoundException("The specified line does not contain the expression token(s): "+this.getInstructionExpressionToken(),this.getInstructionExpressionToken(),line);
        this.setInstructionTokens();
        this.setContentToken();
        this.instruction = this.interpretInstruction();
    }
    
    /**
     * @created 02/10/2004 11:14:32
     * @param line
     * @return if the given line string contains the expression token
     */
    protected boolean containsToken(String line){
        return line.indexOf(this.getInstructionExpressionToken()) != -1;        
    }
    
    /**
     * @created 23/09/2004 13:06:19
     * @return the instruction token used to identify an expression
     */
    public abstract String getInstructionExpressionToken();
    
    /**
     * @created 23/09/2004 12:03:18
     * @return an instruction instance for a given interpretation.
     * @throws LogTokenNotFoundException if the tokens of the interpretation is not found
     */
    protected Instruction interpretInstruction() throws LogInterpreterException{
        Instruction instr = new Instruction(this.content);
        instr.setTime(this.parseInstructionTime(this.dateTimeToken));
        return instr;
    }
    
    /**
     * @created 23/09/2004 14:20:47
     * @return the instruction instance.
     */
    public Instruction getInstruction(){
        return this.instruction;
    }
    
    /**
     * @created 23/09/2004 13:41:29
     * @return the instruction token: NOTE: | DGB1: | START: | END: 
     */
    public String getInstructionToken(){
        return this.instructionToken;
    }
    
    /**
     * @created 23/09/2004 12:02:35
     * @return the line of this instruction
     */
    public String getLine(){
        return this.line;
    }
    
    protected void setInstructionTokens() throws LogTokenNotFoundException{
		StringTokenizer instruction = new StringTokenizer(this.line," ");
		this.instructionToken = instruction.nextToken();
		this.dateTimeToken = instruction.nextToken();		
    }
    
    protected void setContentToken(){
        this.content = this.line.substring(this.line.indexOf(" - ")).substring(3);        
    }
    
    /**
     * @created 23/09/2004 13:12:32
     * @param dateTimeToken
     * @return the instruction time from a token from the line.
     */
    private GregorianCalendar parseInstructionTime(String dateTimeToken) throws LogInterpreterException{
        
        if (dateTimeToken.indexOf("/") == -1)
            throw new BadTokenFormatException("The date-time token is not valid",dateTimeToken,"dd-mm-yyyy/hh:mm:ss[:mill]",this.getLine());
        
        StringTokenizer dateTime = new StringTokenizer(dateTimeToken,"/");
        
		String date = dateTime.nextToken();
		String time = dateTime.nextToken();        
		time = time.replace('.',':');
        
		StringTokenizer dateToken = new StringTokenizer(date,"-");
		
		if (dateToken.countTokens() != 3)
		    throw new BadTokenFormatException("Invalid date format for the instruction",date,"dd-mm-yyyy",this.getLine());		
		
		int day   = Integer.parseInt(dateToken.nextToken());
		int month = Integer.parseInt(dateToken.nextToken());
		int year  = Integer.parseInt(dateToken.nextToken());
		
		StringTokenizer timeToken = new StringTokenizer(time,":");
		
		if (timeToken.countTokens() != 3 && timeToken.countTokens() != 4)
		    throw new BadTokenFormatException("Invalid time format for the instruction",time,"hh:mm:ss[:mill]",this.getLine());
				
		int hour = Integer.parseInt(timeToken.nextToken());
		int min  = Integer.parseInt(timeToken.nextToken());
		int sec  = Integer.parseInt(timeToken.nextToken());
		
		return new GregorianCalendar(year,month-1,day,hour,min,sec);	        
    }
    
	/**
	 * (08/06/2004 02:08:35)
	 * @return a formated representation of the time.
	 */
	public String getFormatedTime(GregorianCalendar time){
		return (time != null)? DateUtils.getFormated(time): "";
	}    
    
	/**
     * @created 23/09/2004 13:50:04
     * @return Returns the content.
     */
    public String getContent() {
        return this.instruction.getContent();
    }
    
    /**
     * @created 23/09/2004 13:50:04
     * @param content The content to set.
     */
    public void setContent(String content) {
        this.instruction.setContent(content);
    }
    
    /**
     * @created 23/09/2004 13:50:04
     * @return Returns the dateTimeToken.
     */
    public String getDateTimeToken() {
        return this.dateTimeToken;
    }
    
    /**
     * @created 23/09/2004 13:50:04
     * @param dateTimeToken The dateTimeToken to set.
     */
    public void setDateTimeToken(String dateTimeToken) {
        this.dateTimeToken = dateTimeToken;
    }
    
    /**
     * @created 23/09/2004 13:50:04
     * @param instructionToken The instructionToken to set.
     */
    public void setInstructionToken(String instructionToken) {
        this.instructionToken = instructionToken;
    }
}
