package interThread;


class Data{
	int value;
	boolean truth=true;
//	set
	synchronized void set(int v) {
		while(truth==false) {
			try {
		wait();	
			}
			catch(Exception e) {
				
			}
		}
		value = v;
		truth=false;
		
		notify();
		
	}
//	get
	synchronized int get() {
		while(truth==true) {
			try {
				wait();
			}
			catch(Exception e) {
				
			}
		}
		truth=true;
		notify();
		
		return value;
		
	}
}


class Producer extends Thread{
	Data d;
	Producer(Data d){
		this.d = d;
	}
	public void run() {
		int count =1;
		while(true) {
			d.set(count);
			System.out.println("Producer " + count);
			count++;
		}
	}
}

class Consumer extends Thread{
	Data d;
	Consumer(Data d){
		this.d = d;
	}
	public void run() {
		int x;
		while(true) {
			 x = d.get();
			System.out.println("Consumer " + x);
			
		}
	}
}

public class interThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data d = new Data();
		Producer p = new Producer(d);
		Consumer c = new Consumer(d);
		p.start();
		c.start();
		
	
		
	}

}
