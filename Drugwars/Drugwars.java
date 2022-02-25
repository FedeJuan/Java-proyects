import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int nulo; int drugs; int chems; int money; //defino las variables numéricas del juego.
    money=5;
    drugs=0;
    chems=0;
    nulo=0; //defino el while para que la respuesta solo comience con 1 o 2
    System.out.println("1.Español 2.English");
    String start = input.nextLine();
    while (nulo==0){
      if (start.equals("1")){ //si la respuesta es 1 se le dan las instrucciones en español.
        instruccionesEsp();
        nulo=1;
      }
      else if (start.equals("2")) { //si la respuesta comienza con 2 se le dan las instrucciones en ingles.
        instruccionesEng();
        nulo=1;
      }
      else if (true){//si nimguna de las anteriores ocurre, se repite el proceso hasta que se ingrese una opción correcta
        System.out.println("No valido");
        System.out.println("1.Español 2.English");
        start = input.nextLine();
      }
    }
    if (start.equals("1")){ //muestra las estadísticas iniciales.
      statsEsp(money, drugs, chems);
    }
    else if (true){
      statsEng(money, drugs, chems);
    }
    if (start.equals("1")){ //aca comienza el juego en su idioma
      OrdenEsp(money, drugs, chems, start);
    }
    else if (true){
      OrdenEng(money, drugs, chems, start);
    }
  }
  // proceso encargado de decir los stats en inglés en cualquier momento del juego/function created to give game stats to the user at any time.
  static void statsEng(int m, int d, int c){
    System.out.println("\nYour money: " + m);
    System.out.println("Your drugs: " + d);
    System.out.println("Your chems: " + c);
  }
  // stats en español/english stats
  static void statsEsp(int m, int d, int c){
    System.out.println("\nTu dinero: " + m);
    System.out.println("Tus drogas: " + d);
    System.out.println("Tus quimicos: " + c);
  }
  //proceso encargado de dar las instrucciones en español/function made to give the game rules.
  static void instruccionesEsp(){
    System.out.println("\nInstrucciones: ");
    System.out.println("Comprar: comprar quimicos");
    System.out.println("Hacer: transforma los quimicos en droga");
    System.out.println("Vender: vender droga");
    System.out.println("Stats: estadisticas del juego");
    System.out.println("Fin: termina el juego");
  }
  //instrucciones en ingles/rules in English.
  static void instruccionesEng(){
    System.out.println("\nRules: ");
    System.out.println("Buy: buy chems");
    System.out.println("Make: transform chems into drugs");
    System.out.println("Sell: sell drugs and make money");
    System.out.println("Stats: game stadistics");
    System.out.println("Exit: end game");
  }
  //proceso realizado para que diga la pregunta de que orden desea hacer el usuario/function made to ask the user what to do next.
  static void PreguntaEsp(){
    System.out.println("\nQue acción desea realizar? (comprar,hacer,vender,stats,fin,reglas)");
  }
  static void PreguntaEng(){
    System.out.println("\nWhat do you want to do? (buy,make,sell,stats,end,rules)");
  }
  static void OrdenEsp(int Mo, int Dr, int Ch, String lengua){ //Juego en español.
    int endgame; //defino la variable que decide cuando terminar el juego
    endgame=0;
    while (endgame==0){
      int OrdenNum; //variable que indica si es 1.compra 2.venta 3.fabricacion
      OrdenNum=0;
      Scanner Scan = new Scanner(System.in); //Scanner encargado de copiar la cantidad que user pone
      int pedido; //variable que copia el scanner y lo pasa a valor numérico.
      pedido=0;
      PreguntaEsp(); //invoco la pregunta para que user decida que quiere hacer
      Scanner input2 = new Scanner(System.in);
      String orden = input2.nextLine(); //variable string que copia literalmente la orden que user escribe y determina que série de acciones corresponden
      if ((orden.equals("comprar")) || (orden.equals("Comprar")) || (orden.equals("COMPRAR"))){
        OrdenNum=1;
        if (Mo<1){ //si no tiene plata no puede comprar nada.
          Accion(pedido, Mo, OrdenNum, lengua);
        }
        else if (Mo>0){
          System.out.println("\nCuantos quimicos queres comprar?");
          pedido = Scan.nextInt();
          if ((pedido<=Mo) & (pedido>0)){ //si tiene plata y compra químicos, se le quita la plata y se le dan los químicos.
            Mo=Mo-pedido;
            Ch=Ch+pedido;
            Accion(pedido, Mo, OrdenNum, lengua);
          }
          else if (pedido==0){ //si tiene plata pero no compra nada...
            Accion(pedido, Mo, OrdenNum, lengua);
          }
          else if ((pedido>Mo) || (pedido<0)){
          Errores(lengua, OrdenNum);
          }
        }
      }
      else if((orden.equals("vender")) || (orden.equals("Vender")) || (orden.equals("VENDER"))){
        OrdenNum=2;
       if (Dr<1){
          Accion(pedido, Mo, OrdenNum, lengua); //si no tiene droga no puede vender.
        }
        else if (Dr>0){
          System.out.println("\nCuanta droga queres vender?");
          pedido = Scan.nextInt();
          if ((pedido<=Dr) & (pedido>0)){
            Dr=Dr-pedido;
            int vendido;
            vendido=pedido*2;
            Mo=Mo+vendido;
            Accion(pedido, vendido, OrdenNum, lengua);
          }
          else if (pedido==0){
            Accion(pedido, Mo, OrdenNum, lengua);
          }
          else if ((pedido>Dr) || (pedido<1)){
            Errores(lengua, OrdenNum);
          }
        }
      }
      else if((orden.equals("hacer")) || (orden.equals("Hacer")) || (orden.equals("HACER"))){
        OrdenNum=3;
        if (Ch<1){
          System.out.println("\nHiciste "+Dr+" drogas.");
        }
        else if (Ch>0){
          System.out.println("\nCuanta droga queres hacer con quimicos?");
          pedido = Scan.nextInt();
          if ((pedido<=Ch) & (pedido>0)){
            Ch=Ch-pedido;
            Dr=Dr+pedido;
            Accion(pedido, Mo, OrdenNum, lengua);
          }
          else if (pedido==0){
            Accion(pedido, Mo, OrdenNum, lengua);
          }
          else if ((pedido>Ch) || (pedido<1)){
            Errores(lengua, OrdenNum);
          }
        }
      }
      else if((orden.equals("stats")) || (orden.equals("Stats")) || (orden.equals("STATS"))){
        statsEsp(Mo, Dr, Ch);
        System.out.println("");
      }
      else if((orden.equals("fin")) || (orden.equals("Fin")) || (orden.equals("FIN"))){
          System.out.println("\nNos vemos!");
          endgame=endgame+1;
      }
      else if ((orden.equals("reglas")) || (orden.equals("Reglas")) || (orden.equals("REGLAS"))){
        System.out.println("");
        instruccionesEsp();
      }
      else if (true){
        System.out.println("\nIntenta de nuevo");
      }
    }
  }  
  static void OrdenEng(int Mo, int Dr, int Ch, String lengua){ //English game
    int endgame; //This will check if the player wants to keep playing
    endgame=0;
    while (endgame==0){
      int OrdenNum;
      OrdenNum=0;
      Scanner Scan = new Scanner(System.in);
      int pedido;
      pedido=0;
      PreguntaEng();
      Scanner input2 = new Scanner(System.in);
      String orden = input2.nextLine();
      if ((orden.equals("buy")) || (orden.equals("Buy")) || (orden.equals("BUY"))){
        OrdenNum=1;
        if (Mo<1){
          Accion(pedido, Mo, OrdenNum, lengua);
        }
        else if (Mo>0){
          System.out.println("\nHow many chems you wanna buy?");
          pedido = Scan.nextInt();
            if ((pedido<=Mo) & (pedido>0)){
              Mo=Mo-pedido;
              Ch=Ch+pedido;
              Accion(pedido, Mo, OrdenNum, lengua);
            }
            else if (pedido==0){
              Accion(pedido, Mo, OrdenNum, lengua);
            }
            else if ((pedido<1) || (pedido>Ch)){
              Errores(lengua, OrdenNum);
            }
        }
      }
      else if((orden.equals("sell")) || (orden.equals("Sell")) || (orden.equals("SELL"))){
        OrdenNum=2;
       if (Dr<1){
          Accion(pedido, Mo, OrdenNum, lengua);
        }
        else if (Dr>0){
          System.out.println("\nHoy many drugs you wanna sell?");
          pedido = Scan.nextInt();
            if ((pedido<=Dr) & (pedido>0)){
              Dr=Dr-pedido;
              int vendido;
              vendido=pedido*2;
              Mo=Mo+vendido;
              Accion(pedido, vendido, OrdenNum, lengua);
            }
            else if (pedido==0){
              Accion(pedido, Mo, OrdenNum, lengua);
            }
            else if ((pedido<1) || (pedido>Dr)){
              Errores(lengua, OrdenNum);
            }
        }
      }
      else if((orden.equals("make")) || (orden.equals("Make")) || (orden.equals("MAKE"))){
        OrdenNum=3;
        if (Ch<1){
          Accion(pedido, Mo, OrdenNum, lengua);
        }
        else if (Ch>0){
          System.out.println("\nHow many drugs you wanna make?");
          pedido = Scan.nextInt();
            if ((pedido<=Ch) & (pedido>0)){
              Ch=Ch-pedido;
              Dr=Dr+pedido;
              Accion(pedido, Mo, OrdenNum, lengua);
            }
            else if (pedido==0){
              Accion(pedido, Mo, OrdenNum, lengua);
            }
            else if ((pedido<1) || (pedido>Ch)){
              Errores(lengua, OrdenNum);
            }
        }
      }
      else if((orden.equals("stats")) || (orden.equals("Stats")) || (orden.equals("STATS"))){
        statsEng(Mo, Dr, Ch);
        System.out.println("");
      }
      else if((orden.equals("exit")) || (orden.equals("Exit")) || (orden.equals("EXIT"))){
          System.out.println("\nSee ya!");
          endgame=endgame+1;
      }
      else if ((orden.equals("rules")) || (orden.equals("Rules")) || (orden.equals("RULES"))){
        System.out.println("");
        instruccionesEng();
      }
      else if (true){
        System.out.println("\nTry Again.");
      }
    }
  } 
  static void Accion(int num1, int num2, int orden, String lenguaje){ //
    if (lenguaje.equals("1")){
      if (orden == 1){
        System.out.println("\nCompraste "+num1+" quimicos");
      }
      else if (orden == 2){
        System.out.println("\nVendiste "+num1+" drogas");
        System.out.println("Hiciste "+num2+" dinero.");
      }
      else if(true){
        System.out.println("\nHiciste "+num1+" drogas");
      }
    }
    else if (lenguaje.equals("2")){
      if (orden == 1){
        System.out.println("\nYou bought "+num1+" chems");
      }
      else if (orden == 2){
        System.out.println("\nYou sold "+num1+" drugs");
        System.out.println("You made "+num2+" money.");
      }
      else if(true){
        System.out.println("\nYou made "+num1+" drugs");
      }
    }
  }
  static void Errores(String lenguaje, int orden){ //Subproceso para arrojar errores del user al querer comprar
    if (lenguaje.equals("1")){
      if (orden==1){
        System.out.println("\nNo podes comprar.");
      }
      else if (orden==2){
        System.out.println("\nNo podes vender.");
      }
      else if (orden==3){
        System.out.println("\nNo podes hacer.");
      }
    }
    else if (lenguaje.equals("2")){
      if (orden==1){
        System.out.println("\nYou can't buy.");
      }
      else if (orden==2){
        System.out.println("\nYou can't sell.");
      }
      else if (orden==3){
        System.out.println("\nYou can't make.");
      }
    }
  }
}
