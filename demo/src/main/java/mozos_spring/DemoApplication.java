package mozos_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
		
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

/*
1. Funcionalidades principales
Para administradores (quienes crean las encuestas):

Gestión de encuestas:

Crear, editar y eliminar encuestas.
Definir tipos de preguntas: opción múltiple, texto abierto, checkbox, escala de puntajes, etc.
Establecer fecha de inicio y fin de la encuesta.


Gestión de preguntas:

Añadir preguntas a encuestas.
Ordenar preguntas.


Accesos y restricciones:

Definir si una encuesta será pública (cualquier usuario) o privada (usuarios registrados).


Visualización de resultados:

Mostrar resultados en gráficos interactivos (barras, torta, etc.).
Exportar resultados a formatos como Excel o PDF.


Para usuarios (quienes responden las encuestas):
Acceso a encuestas públicas o privadas:

Consultar encuestas disponibles.
Responder encuestas (asegurarse de evitar respuestas duplicadas).


Historial de respuestas (si están registrados):

Consultar las encuestas que HAN RESPONDIDO. */
