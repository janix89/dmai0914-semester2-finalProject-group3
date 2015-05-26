import java.util.EventObject;


public class AnyEvent extends EventObject {
	private String buttonTrigered;
	
	public AnyEvent(Object source){
		super(source);
	}
	
	public AnyEvent(Object source, String buttonTrigered){
		super(source);
		this.buttonTrigered = buttonTrigered;
	}

	public String getButtonTrigered() {
		return buttonTrigered;
	}

	public void setButtonTrigered(String buttonTrigered) {
		this.buttonTrigered = buttonTrigered;
	}
}
