package UTDC;

/**
 * Classe que abstrai a utilização da classe Scanner, escondendo todos os
 * problemas relacionados com excepções, e que oferece métodos simples e
 * robustos para a leitura de valores de tipos simples e String.
 *
 * É uma classe de serviços, como Math e outras de Java, pelo que devem ser
 * usados os métodos de classe implementados.
 *
 * Utilizável em BlueJ, NetBeans, CodeBlocks ou Eclipse.
 * 
 * Utilização típica:  int x = UTDC.Input.lerInt();
 *                     String nome = UTDC.Input.lerString();
 * 
 * @author F. Mário Martins
 * @version 1.0 (6/2006)
 *
 * @authors João V.; Miguel Q.; Simão B.
 * @version 2.0 (12/2018)
 */
import static java.lang.System.out;
import static java.lang.System.in;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class Input {

 /**
  * Métodos de Classe
  */

 public static LocalDate lerDate(String format){
     Scanner input = new Scanner(in);
     boolean ok = false;
     String date_txt;
     LocalDate dt = null;
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
     while(!ok){
         try{
             out.println("(Expected format: "+format);
             date_txt = input.nextLine();
             dt = LocalDate.parse(date_txt, formatter);
             ok = true;
         }catch(InputMismatchException | DateTimeParseException e){
             out.println("Invalid date, try again!");
         }
     }
     return dt;
 }

 public static LocalDateTime lerDateTime(String format){
    Scanner input = new Scanner(in);
    boolean ok = false;
    String date_txt;
    LocalDateTime dt = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    while(!ok){
        try{
            out.println("(Expected format: "+ format);
            date_txt = input.nextLine();
            dt = LocalDateTime.parse(date_txt, formatter);
            ok = true;
        }catch(InputMismatchException | DateTimeParseException e){
            out.println("Invalid date, try again!");
        }
    }
    return dt;
}

 public static LocalTime lerTime(String format){
    Scanner input = new Scanner(in);
    boolean ok = false;
    String date_txt;
    LocalTime lt = null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    while(!ok){
        try{
            out.println("(Expected format: " + format);
            date_txt = input.nextLine();
            lt = LocalTime.parse(date_txt, formatter);
            ok = true;
        }catch(InputMismatchException | DateTimeParseException e){
            out.println("Invalid time, try again!");
        }
    }
    return lt;
 }

 public static DayOfWeek lerWeekDay(){
     Scanner input = new Scanner(in);
     boolean ok = false;
     String date_txt;
     DayOfWeek dayOfWeek = null;
     List<String> days = Arrays.asList(DayOfWeek.values())
             .stream()
             .map(d -> d.getDisplayName(TextStyle.FULL, Locale.ENGLISH))
             .collect(Collectors.toList());
     while(!ok){
         date_txt = input.nextLine().toUpperCase();
         try{
             dayOfWeek = DayOfWeek.valueOf(date_txt);
             ok = true;
         }catch(Exception e){
             out.println("Invalid day of week, try again!");
         }
     }
     return dayOfWeek;
 }

 public static long lerLong() {
     Scanner input = new Scanner(in);
     boolean ok = false;
     long i = 0;
     while(!ok) {
        try {
           i = input.nextLong();
           ok = true;
        }
       catch(InputMismatchException e)
       { out.println("Long Invalido");
         out.print("Novo valor: ");
         input.nextLine();
       }
     }
     //input.close();
     return i;
 }
    
 public static String lerString() {
     Scanner input = new Scanner(in);
     boolean ok = false;
     String txt = "";
     while(!ok) {
         try {
             txt = input.nextLine();
             ok = true;
         }catch(InputMismatchException | DateTimeParseException e){
             out.println("Texto Invalido");
             out.print("Novo valor: ");
             input.nextLine();
         }
     }
     //input.close();
     return txt;
  } 

 
 public static int lerInt() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     int i = 0; 
     while(!ok) {
         try {
             i = input.nextInt();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Inteiro Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return i;
  } 
  
  public static double lerDouble() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     double d = 0.0; 
     while(!ok) {
         try {
             d = input.nextDouble();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return d;
  }  
  
   public static float lerFloat() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     float f = 0.0f; 
     while(!ok) {
         try {
             f = input.nextFloat();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Valor real Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return f;
  }  
  
   public static boolean lerBoolean() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     boolean b = false; 
     while(!ok) {
         try {
             b = input.nextBoolean();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Booleano Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return b;
  } 
  
  public static short lerShort() {
     Scanner input = new Scanner(in);
     boolean ok = false; 
     short s = 0; 
     while(!ok) {
         try {
             s = input.nextShort();
             ok = true;
         }
         catch(InputMismatchException e) 
             { out.println("Short Invalido"); 
               out.print("Novo valor: ");
               input.nextLine(); 
             }
     }
     //input.close();
     return s;
  }  
}
