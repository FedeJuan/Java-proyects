import java.util.Scanner;
import java.util.Random;
class Main {
  public static void main(String[] args) {
      intro();
  }
  // el juego lo pongo en una funcion llamada intro porque si el usuario indica "exit", no se puede retornar a main, pero si a intro.
  // en intro printeo el cartel de bienvenida y las opciones numericas para elegir contra que oponente jugar.
  // esa informacion llamada start o, en la siguiente funcion, opcion, sirve para la funcion swticheo.
  static void intro(){
    System.out.println("\nContra quien queres jugar?");
    System.out.println("\n1.Copycat\n2.Criminal\n3.Abuela\n4.Detective\n5.Grudger\n6.Copykid\n7.Simpleton\n8.Payaso\n9.Personalizado\n10.Random\n");
    System.out.println("\nElegi un numero");
    Scanner input = new Scanner(System.in);
    int start = input.nextInt();
    if ((start < 1) || (start > 10)){
      System.out.println("Incorrecto.");
      intro();
    }
    JuegoTrust(0, 0, 0, start, 0, true, false, 0);
  }
  // Luego de elegir el oponente, hago una funcion para que el jugador elija el tipo de movimiento (1 si es cheat 2 si es cooperate). Tambien
  // se inicializa el puntaje de cada jugador, el turno, la opcion del oponente ingresada por el usuario, la variable respuesta esta hecha para que
  // guarde el tipo de movimiento hecho la ultima ronda (si fue cheat es true y si fue coop es false), la variable cheatdetector es una variable que
  // registra si el usuario alguna vez eligio la opcion cheat, fundamental para el funcionamiento del personaje detective. amount funciona como un
  // contador de veces consecutivas que un jugador eligio una opcion, necesaria para copykid, simpleton y personalizado.
  static void JuegoTrust(int puntosjugador, int puntosoponente, int turno, int opcion, int movimiento, boolean respuesta, boolean cheatdetector, int amount){
    boolean exit = true;
    while (exit == true){
      System.out.println("\nque movimiento queres realizar? (cheat/cooperate)");
      Scanner input = new Scanner(System.in);
      String start = input.nextLine();
      if ((start.equals("Cheat")) || (start.equals("cheat"))){
        movimiento = 1;
        cheatdetector = true;
        exit = false;
      }
      else if ((start.equals("Cooperate")) || ((start.equals("cooperate")))){
        movimiento = 2;
        exit = false;
      }
      else if ((start.equals("Exit")) || ((start.equals("exit")))){
        intro();
      }
      else
      System.out.println("Incorrecto.");
    }
    switcheo (opcion, turno, respuesta, puntosjugador, puntosoponente, movimiento, cheatdetector, amount);
  }
  // esta funcion es basicamente el switch con todas las opciones de jugadores y como opera cada uno. El motivo por el cual lo separe de JuegoTrust
  // es porque varios jugadores, cuando ocurren un par de interacciones, operan de forma identica a otro jugador ya programado, asi que utilizo la
  // recursion para "convertir" a ese jugador en otro (ejemplo cuando se elije la opcion cheat, grudger se convierte en criminal)
  static void switcheo(int opcion, int turno, boolean respuesta, int puntosjugador, int puntosoponente, int movimiento, boolean cheatdetector, int amount){
    switch (opcion){
      case 1 :
        if ((turno == 0) || (respuesta == false)){
          if (movimiento == 1){
            cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
          }
          else {
             cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
          }
        }
        else {
           if (movimiento == 1){
            cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
           }
            else
              cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
        }
      break;

      case 2 :
        if (movimiento == 1) {
          cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
        }
         else
          cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
      break;

      case 3 :
        if (movimiento == 1) {
          cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
        }
         else
           cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
      break;

      case 4:
        if (turno == 1){
          if (movimiento == 1) {
            cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
          }
          else
            cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
        }
        else if (turno <= 3) {
            if (movimiento == 1) {
              cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
            }
            else
              cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
        }
        else if (cheatdetector == true){
              switcheo (1, turno, respuesta, puntosjugador, puntosoponente, movimiento, cheatdetector, amount);
        }
        else
          switcheo (2, turno, respuesta, puntosjugador, puntosoponente, movimiento, cheatdetector, amount);
      break;

      case 5:
        if (turno == 0){
          if (movimiento == 1) {
            cambiapuntos(puntosjugador, puntosoponente, 2, turno, 2, movimiento, cheatdetector, amount);
          }
          else
            cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
        }
        else if (movimiento == 1){
          cambiapuntos(puntosjugador, puntosoponente, 2, turno, 2, movimiento, cheatdetector, amount);
        }
        else
          cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
      break;

      case 6 :
        if (amount < 2){
          if (movimiento == 1){
            amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
          }
          else {
             amount = 0;
             cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
          }
        }
        else {
           if (movimiento == 1){
             amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
           }
            else{
              amount = 0;
              cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
            }
        }
      break;

      case 7 :
        if ((turno == 0)  || (amount % 2 == 0)){
          if (movimiento == 1) {
            amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
          }
          else {
             amount = 0;
             cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
          }
        }
        else {
          if (movimiento == 1){
            amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
          }
          else {
            amount = 1;
            cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
          }
        }
      break;

      case 8 :
        Random rand = new Random();
        if (movimiento == 1){
          int numero = (rand.nextInt(2)*2);
          cambiapuntos(puntosjugador, puntosoponente, numero, turno, opcion, movimiento, cheatdetector, amount);
        }
        else{
          int numero = ((rand.nextInt(2)*2) + 1);
          cambiapuntos(puntosjugador, puntosoponente, numero, turno, opcion, movimiento, cheatdetector, amount);
        }
      break;

      case 9 :
        if ((respuesta == true) || (amount % 2 == 0)){
          if (movimiento == 1) {
            amount = 0;
            cambiapuntos(puntosjugador, puntosoponente, 4, turno, opcion, movimiento, cheatdetector, amount);
          }
          else{
            amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 3, turno, opcion, movimiento, cheatdetector, amount);
         }
        }
        else if (amount % 2 == 1){
          if (movimiento == 1) {
            amount = 0;
            cambiapuntos(puntosjugador, puntosoponente, 2, turno, opcion, movimiento, cheatdetector, amount);
          }
          else{
            amount = amount + 1;
            cambiapuntos(puntosjugador, puntosoponente, 1, turno, opcion, movimiento, cheatdetector, amount);
          }
        }
      break;

      case 10 :
        Random aleatorio = new Random();
        int numero = (aleatorio.nextInt(10)+1);
        switcheo (numero, turno, respuesta, puntosjugador, puntosoponente, movimiento, cheatdetector, amount);
      break;
    }
  }
  // funciones que se encargan de recibir un resultado determinado, modificar las puntuaciones y arrojar las puntuaciones de cada uno, ademas de 
  // modificar la ultima respuesta elegida por el usuario correspondientemente y aumentar en uno el numero del turno.
  static void puntuacion(int puntosjugador, int puntosoponente){
    System.out.println("Puntos jugador: " + puntosjugador);
    System.out.println("Puntos computadora: " + puntosoponente);
  }
  static void cambiapuntos (int puntosjugador, int puntosoponente, int resultado, int turno, int opcion, int movimiento, boolean cheatdetector, int amount){
    boolean respuesta = true;
    turno = turno + 1;
    if (resultado == 1) {
      puntosjugador = puntosjugador + 2;
      puntosoponente = puntosoponente + 2;
      puntuacion(puntosjugador, puntosoponente);
      respuesta = false;
    }
    else if (resultado == 2) {
      puntosjugador = puntosjugador + 3;
      puntosoponente = puntosoponente - 1;
      puntuacion(puntosjugador, puntosoponente);
      respuesta = true;
    }
    else if (resultado == 3){
      puntosjugador = puntosjugador - 1;
      puntosoponente = puntosoponente + 3;
      puntuacion(puntosjugador, puntosoponente);
      respuesta = false;
    }
    else {
      puntuacion(puntosjugador, puntosoponente);
      respuesta = true;
    }
    JuegoTrust(puntosjugador, puntosoponente, turno, opcion, movimiento, respuesta, cheatdetector, amount);
  }
}
