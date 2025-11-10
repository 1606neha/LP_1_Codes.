import java.util.*;
import java.io.*;

public class Pass2 {
    static Obj[] symbol_table = new Obj[20];
    static Obj[] literal_table = new Obj[20];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.print("ENTER TOTAL NUMBER OF SYMBOLS: ");
        int total = sc.nextInt();
        for (int i = 0; i < total; i++) {
            System.out.print("ENTER SYMBOL NAME: ");
            String name = sc.next();
            System.out.print("ENTER SYMBOL ADDRESS: ");
            int addr = sc.nextInt();
            symbol_table[i] = new Obj(name, addr);
        }

        System.out.print("\nENTER TOTAL NUMBER OF LITERALS: ");
        int total_lit = sc.nextInt();
        for (int i = 0; i < total_lit; i++) {
            System.out.print("ENTER LITERAL NAME: ");
            String name = sc.next();
            System.out.print("ENTER LITERAL ADDRESS: ");
            int addr = sc.nextInt();
            literal_table[i] = new Obj(name, addr);
        }

        BufferedReader br = new BufferedReader(new FileReader("Output.txt"));
        String line;

        System.out.println("***** Final Machine Code *****");

        while ((line = br.readLine()) != null) {
            String[] tokens = line.trim().split("\\s+");
            boolean temp = false;

            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];

                // location counter
                if (token.matches("[0-9]+")) {
                    System.out.print("\n" + token + "\t");
                }
                // imperative statement
                else if (token.matches("\\(IS,\\d+\\)")) {
                    String opcode = token.replaceAll("[^0-9]", "");
                    System.out.print(opcode + "\t");
                }
                // register
                else if (token.matches("\\(RG,\\d+\\)")) {
                    String reg = token.replaceAll("[^0-9]", "");
                    System.out.print(reg + "\t");
                }
                // constant
                else if (token.matches("\\(C,\\d+\\)")) {
                    String constant = token.replaceAll("[^0-9]", "");
                    System.out.print(constant + "\t");
                }
                // symbol
                else if (token.matches("\\(S,\\d+\\)")) {
                    int index = Integer.parseInt(token.replaceAll("[^0-9]", ""));
                    if (index - 1 < symbol_table.length && symbol_table[index - 1] != null) {
                        System.out.print(symbol_table[index - 1].addr + "\t");
                    }
                }
                // literal
                else if (token.matches("\\(L,\\d+\\)")) {
                    int index = Integer.parseInt(token.replaceAll("[^0-9]", ""));
                    if (index - 1 < literal_table.length && literal_table[index - 1] != null) {
                        System.out.print(literal_table[index - 1].addr + "\t");
                    }
                }
                // DC statement
                else if (token.matches("\\(DL,2\\)")) {
                    if (i < tokens.length - 1 && tokens[i + 1].matches("\\(C,\\d+\\)")) {
                        String constant = tokens[++i].replaceAll("[^0-9]", "");
                        System.out.print("00\t00\t" + constant + "\t");
                    }
                }
              
                else if (token.matches("\\(DL,1\\)") || token.matches("\\(AD,\\d+\\)")) {
                    temp = true;
                    break;
                }
            }
            if (temp) {
                System.out.print("----\t");
            }
        }
        br.close();
        sc.close();
    }
}

class Obj {
    String name;
    int addr;

    Obj(String name, int addr) {
        this.name = name;
        this.addr = addr;
    }
}

