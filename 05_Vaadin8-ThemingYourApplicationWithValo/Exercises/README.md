# Ejecutar en VSCode

**Ejecutar sin Debug**

- Abrir pestaña Java Projects
- Botón derecho sobre exercises-themeing
  - Seleccionar Maven
  - Seleccionar Run Maven Commands...
- Seleccionar Custom ...
  - Hacer que ejecute `jetty:run`
- Abrir el navegador e ir a la ruta `http://localhost:8080/`

**Añadir debug a VSCode**

- Crear carpeta .vscode en la raiz del proyecto
  - Crear fichero launch.json con el contenido
    ```
    	"version": "0.2.0",
    	"configurations": [
    		{
    			"type": "java",
    			"name": "Debug (Attach)",
    			"request": "attach",
    			"hostName": "localhost",
    			"port": 8000
    		}
    	]
    }
    ```
- Ejecutar en el terminal, en la carpeta del proyecto (el puerto debe coincidir con el indicado en launch.json):
  - `export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE"`
- Ejecutar:
  - `mvn jetty:run -f "/Users/jmmm/Programacion/Programacion/Java/Vaadin/Videos/05_Vaadin8-ThemingYourApplicationWithValo/Exercises/pom.xml"`
- Pulsar F5 en VSCode
- Acceder a la ruta `http://localhost:8080/`
- Cuando accedamos a la parte de la app donde tenemos puntos de interrupción, se lanzará el debug

**Hot Reload**

- Para hot reload de Jetty añadir en la configuración `<scanIntervalSeconds>2</scanIntervalSeconds>`, pero tarda unos 5 sg.

```
			<plugin>
				<groupId>org.eclipse.jetty</groupId>
				<artifactId>jetty-maven-plugin</artifactId>
				<version>9.3.9.v20160517</version>
				<configuration>
					<scanIntervalSeconds>2</scanIntervalSeconds>
					<webApp>
						<contextPath>/</contextPath>
					</webApp>
				</configuration>
			</plugin>
		</plugins>
```
