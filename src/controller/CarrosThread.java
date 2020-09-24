package controller;

import java.util.concurrent.Semaphore;

public class CarrosThread extends Thread{
	private Semaphore semaforo;
	private int id;
	private String sentido;
	
	public CarrosThread(Semaphore semaforo, int id) {
		this.semaforo = semaforo;
		this.id = id;
	}
	
	@Override
	public void run() {
		qualSentido();
		try {
			this.semaforo.acquire();
			passando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			this.semaforo.release();
		}
	}
	
	private void passando() {
		System.out.println("O carro " + this.id + " passou no sentido " + this.sentido);
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void qualSentido(){
		switch(this.id) {
			case 1: this.sentido = "Norte";
				break;
			case 2: this.sentido = "Sul";
				break;
			case 3: this.sentido = "Leste";
				break;
			case 4: this.sentido = "Oeste";
		}
	}
}
