package org.example;

import javax.swing.*;

public class Main {
        public static void main(String[]args){
            Serie crematorio=new Serie();
            crematorio.setPlataforma("Digital +");
            crematorio.setNombre("Crematorio");
            crematorio.setCreador("Rafael Chirbes");
            crematorio.setAnyo(2009);
            Serie mejor=new Serie(JOptionPane.showInputDialog("Introduce la Plataforma"), JOptionPane.showInputDialog("Introduce el Nombre"), JOptionPane.showInputDialog("Introduce el Creador"),Integer.parseInt(JOptionPane.showInputDialog("Introduce el Año de Creación")));
        }
    }