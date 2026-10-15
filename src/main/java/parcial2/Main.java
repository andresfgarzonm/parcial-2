package parcial2;
import parcial2.model.FuncionCinematografica;
import parcial2.model.Pelicula;
import parcial2.model.Sala;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Instancia principal del cine basada en el modelo CinePlus
        CinePlus cinePlus = new CinePlus("CinePlus Armenia", "900987654", "www.cineplus.com");

        int opcion = 0;

        while (opcion != 10) {

            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, """
                            ================ CINEPLUS ================
                            1. Registrar cliente
                            2. Registrar película
                            3. Registrar sala
                            4. Registrar función cinematográfica
                            5. Agregar cliente a función (Vender entradas)
                            6. Buscar cliente por número de teléfono
                            7. Verificar teléfono perfecto de cliente
                            8. Calcular ingresos por fecha
                            9. Listar funciones
                            10. Salir

                            Seleccione una opción:
                            """));

            if (opcion == 1) {
                String nombreCompleto = JOptionPane.showInputDialog(null, "Ingrese el nombre completo:");
                String documentoIdentidad = JOptionPane.showInputDialog(null, "Ingrese el documento de identidad:");
                String numeroTelefono = JOptionPane.showInputDialog(null, "Ingrese el número de teléfono:");
                String correoElectronico = JOptionPane.showInputDialog(null, "Ingrese el correo electrónico:");
                String codigoCliente = JOptionPane.showInputDialog(null, "Ingrese el código de cliente:");
                double porcentajeDescuento = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el porcentaje de descuento (ej: 0.10):"));

                String resultado = cinePlus.registrarCliente(nombreCompleto, documentoIdentidad, numeroTelefono, correoElectronico, codigoCliente, porcentajeDescuento);
                JOptionPane.showMessageDialog(null, resultado);

            } else if (opcion == 2) {
                int codigoPelicula = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de la película:"));
                String titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la película:");
                int duracion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la duración en minutos (ej: 120):"));

                String generoStr = JOptionPane.showInputDialog(null, """
                        Ingrese el género cinematográfico:
                        ACCION
                        COMEDIA
                        DRAMA
                        TERROR
                        ANIMACION
                        """);

                String clasificacionStr = JOptionPane.showInputDialog(null, """
                        Ingrese la clasificación por edad:
                        TODOS
                        MAYORES_12
                        MAYORES_15
                        MAYORES_18
                        """);

                String resultado = cinePlus.registrarPelicula(codigoPelicula, titulo, duracion, generoStr, clasificacionStr);
                JOptionPane.showMessageDialog(null, resultado);

            } else if (opcion == 3) {
                int numeroSala = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la sala:"));
                int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad máxima:"));
                String tipoSala = JOptionPane.showInputDialog(null, """
                        Seleccione el tipo de sala:
                        1. Estándar
                        2. 3D
                        3. VIP
                        """);

                String resultado = cinePlus.registrarSala(numeroSala, capacidad, tipoSala);
                JOptionPane.showMessageDialog(null, resultado);

            } else if (opcion == 4) {
                int codigoPelicula = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de la película:"));
                Pelicula pelicula = cinePlus.buscarPelicula(codigoPelicula);

                if (pelicula != null) {
                    int numeroSala = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de la sala:"));
                    Sala sala = cinePlus.buscarSala(numeroSala);

                    if (sala != null) {
                        String codigoFuncion = JOptionPane.showInputDialog(null, "Ingrese el código de la función:");
                        String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha (AAAA-MM-DD):");
                        String horaInicio = JOptionPane.showInputDialog(null, "Ingrese la hora de inicio (HH:MM):");
                        double precioEntrada = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio de la entrada:"));

                        String resultado = cinePlus.registrarFuncion(codigoFuncion, fecha, horaInicio, precioEntrada, pelicula, sala);
                        JOptionPane.showMessageDialog(null, resultado);
                    } else {
                        JOptionPane.showMessageDialog(null, "La sala especificada no existe.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La película especificada no existe.");
                }

            } else if (opcion == 5) {
                String codigoFuncion = JOptionPane.showInputDialog(null, "Ingrese el código de la función:");
                FuncionCinematografica funcion = cinePlus.buscarFuncion(codigoFuncion);

                if (funcion != null) {
                    String documento = JOptionPane.showInputDialog(null, "Ingrese el documento del cliente:");
                    Cliente cliente = cinePlus.buscarCliente(documento);

                    if (cliente != null) {
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de boletos:"));
                        String resultado = funcion.agregarCliente(cliente, cantidad);
                        JOptionPane.showMessageDialog(null, resultado);
                    } else {
                        JOptionPane.showMessageDialog(null, "El cliente no se encuentra registrado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La función cinematográfica no existe.");
                }

            } else if (opcion == 6) {
                String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del cliente:");
                Cliente cliente = cinePlus.buscarClientePorTelefono(telefono);

                if (cliente != null) {
                    JOptionPane.showMessageDialog(null, "Cliente encontrado:\n\n" + cliente.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No existe un cliente registrado con ese número de teléfono.");
                }

            } else if (opcion == 7) {
                String telefono = JOptionPane.showInputDialog(null, "Ingrese el teléfono del cliente:");
                Cliente cliente = cinePlus.buscarClientePorTelefono(telefono);

                if (cliente != null) {
                    String resultado = cinePlus.verificarNumeroPerfecto(cliente.getNumeroTelefono());
                    JOptionPane.showMessageDialog(null, "Cliente: " + cliente.getNombreCompleto()
                            + "\nTeléfono: " + cliente.getNumeroTelefono()
                            + "\nResultado: " + resultado);
                } else {
                    JOptionPane.showMessageDialog(null, "No existe un cliente registrado con ese número de teléfono.");
                }

            } else if (opcion == 8) {
                String fecha = JOptionPane.showInputDialog(null, "Ingrese la fecha a consultar (AAAA-MM-DD):");
                double ingresos = cinePlus.calcularIngresosFecha(fecha);
                JOptionPane.showMessageDialog(null, "Ingresos totales para la fecha " + fecha + ":\n$" + ingresos);

            } else if (opcion == 9) {
                String listaFunciones = cinePlus.listarFunciones();
                JOptionPane.showMessageDialog(null, listaFunciones);

            } else if (opcion == 10) {
                JOptionPane.showMessageDialog(null, "Saliendo del sistema CinePlus.");
            } else {
                JOptionPane.showMessageDialog(null, "La opción seleccionada no es válida.");
            }
        }
    }
}