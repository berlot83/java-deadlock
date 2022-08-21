<h3>Simulación de deadlock entre dos threads</h3>

<p><u><strong>IMPRIME:</strong></u></p>
<ul>
<li>Primer proceso: mundo --> imprime OK</li>
<li>thread-1: TIMED_WAITING --> queda en este estado por el sleep( ) y no cambia porque entró en deadlock</li>
<li>thread-2: BLOCKED --> se bloquea cuando intenta hacer el acquire del proceso que ya tiene el otro thread</li>
<li>thread-3: TERMINATED --> el thread-3 se ejecutó con normalidad sin depender de los otros dos que quedaron en deadlock</li>
<li>Primer proceso: hola --> imprime OK</li>
</ul>		

<p>Nunca imprime <i>NO HAY DEADLOCK</i> porque se quedan esperando a que se liberen los procesos mutuamente</p>
		
<p><u><strong>Posibles estados de los Threads:<strong></u></p>		
<ul>
<li>New</li>
<li>Runnable</li>
<li>Blocked</li>
<li>Waiting</li>
<li>Timed Waiting</li>
<li>Terminated</li>
</ul>		
		
<p>El programa así como esta <i>queda en ejecución infinita y no termina</i>, ya que los dos threads se quedan esperando mutuamente, si le quitamos el <i>sleep( )</i>, ejecuta correctamente, si le agregamos un <i>sleep( )</i> a los dos Threads sin importar el tiempo tenemos el mismo deadlock.</p>