package com.molokotech;

public class DeadlockExample {

	static String hello = "hola";
	static String world = "mundo";

	public static void main(String[] args) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (hello) {
					try {
						Thread.sleep(100);
						System.out.println("Primer proceso: " + hello);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
					synchronized (world) {
						System.out.println("Segundo proceso: NO HAY DEADLOCK - imprime " + world);
					}
				}
			}
		}, "thread-1");
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (world) {
						System.out.println("Primer proceso: " + world);
					synchronized (hello) {
						System.out.println("Segundo proceso: NO HAY DEADLOCK - imprime " + hello);
					}
				}
			}
		}, "thread-2");
		t2.start();

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("continua ejecutando");
			}
		}, "thread-3");
		t3.start();
		
		System.out.println(t1.getName() + ": " + t1.getState());
		System.out.println(t2.getName() + ": " + t2.getState());
		System.out.println(t3.getName() + ": " + t3.getState());

	}
	
	/**
	 *  IMPRIME
	 *  
	 *  Primer proceso: mundo --> imprime OK
		thread-1: TIMED_WAITING --> queda en este estado por el sleep( ) y no cambia porque entró en deadlock
		thread-2: BLOCKED --> se bloquea cuando intenta hacer el acquire del proceso que ya tiene el otro thread
		thread-3: TERMINATED --> el thread-3 se ejecutó con normalidad sin depender de los otros dos que quedaron en deadlock
		Primer proceso: hola --> imprime OK
		
		Nunca imprime NO HAY DEADLOCK porque se quedan esperando a que se liberen los procesos mutuamente
		
		Posibles estados de los Threads:
		- New
		- Runnable
		- Blocked
		- Waiting
		- Timed Waiting
		- Terminated
		
		El programa así como esta queda en ejecución infinita y no termina, ya que los dos threads se quedan esperando mutuamente, si le quitamos el sleep( ), ejecuta correctamente,
		si le agregamos un sleep( ) a los dos Threads sin importar el tiempo tenemos el mismo deadlock
	 */

}
