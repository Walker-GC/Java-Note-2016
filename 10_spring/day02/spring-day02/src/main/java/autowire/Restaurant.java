package autowire;

public class Restaurant {
	private Waiter wt;

	public Restaurant() {
		System.out.println("Restaurant()");
	}

	public Waiter getWt() {
		return wt;
	}

	public void setWt(Waiter wt) {
		System.out.println(
				"Restaurant's setWt()");
		this.wt = wt;
	}

	@Override
	public String toString() {
		return "Restaurant [wt=" + wt + "]";
	}
	
	
	
	
	
}
