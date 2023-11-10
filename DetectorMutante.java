import java.util.Scanner;

public class MutanteDetector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la matriz de ADN (6x6):");

        // Pedir y validar la matriz de ADN
        String[] dna = new String[6];
        for (int i = 0; i < 6; i++) {
            boolean validInput = false;
            do {
                System.out.print("Fila " + (i + 1) + ": ");
                String row = scanner.nextLine().toUpperCase();
                if (row.matches("[ATCG]+")) {
                    dna[i] = row;
                    validInput = true;
                } else {
                    System.out.println("Error: La Fila debe ser de 6 elementos. Ingrese solo los caracteres A, T, C, G.");
                }
            } while (!validInput);
        }
        System.out.println("\n-----------------------------");
        System.out.println("     <<<ADN MUTANTES>>>     ");
        System.out.println("-----------------------------");

        // Mostrar la matriz ingresada
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(dna[i].charAt(j) + " ");
            }
            System.out.println();
        }

        // Verificar si es mutante
        int contador = 0;

        // Verificar horizontalmente
        for (String row : dna) {
            contador += contadorMutantesSecuencia(row);
        }

        // Verificar verticalmente
        for (int i = 0; i < 6; i++) {
            StringBuilder column = new StringBuilder();
            for (String row : dna) {
                column.append(row.charAt(i));
            }
            contador += contadorMutantesSecuencia(column.toString());
        }

        // Verificar en diagonales
        for (String diagonal : getDiagonals(dna)) {
            contador += contadorMutantesSecuencia(diagonal);
        }

        // Mostrar el resultado
        if (contador > 1) {
            System.out.println("¡Es mutante!");
        } else {
            System.out.println("No es mutante.");
        }
    }

    // Función para contar las secuencias mutantes en una cadena
    public static int contadorMutantesSecuencia(String secuencia) {
        int contador = 0;
        for (int i = 0; i < secuencia.length() - 3; i++) {
            if (secuencia.charAt(i) == secuencia.charAt(i + 1) &&
                    secuencia.charAt(i) == secuencia.charAt(i + 2) &&
                    secuencia.charAt(i) == secuencia.charAt(i + 3)) {
                contador++;
            }
        }
        return contador;
    }

    // Función para obtener las diagonales de una matriz
    public static String[] getDiagonals(String[] dna) {
        String[] resultado = new String[11];
        int index = 0;

        // Diagonales principales
        for (int i = 0; i < 6; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int j = 0; j + i < 6; j++) {
                diagonal.append(dna[j].charAt(j + i));
            }
            resultado[index++] = diagonal.toString();
        }

        // Diagonales secundarias
        for (int i = 1; i < 6; i++) {
            StringBuilder diagonal = new StringBuilder();
            for (int j = 0; j + i < 6; j++) {
                diagonal.append(dna[j + i].charAt(j));
            }
            resultado[index++] = diagonal.toString();
        }

        return resultado;
    }
}
