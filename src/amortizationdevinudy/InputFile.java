package amortizationdevinudy;
public class InputFile extends Keyboard
{
    
    public InputFile(String fn)
    {
        super(fn);
    }
    
    
    public boolean eof( )
  {
    int next = 0;

    try {
      next = in.read( );
    }
    catch( Exception e )
    {
      next = -1;
    }
    if( next != -1 )
    {
      unread( (char)next );
      return false;
    }
    else
        return true;
  }
}
