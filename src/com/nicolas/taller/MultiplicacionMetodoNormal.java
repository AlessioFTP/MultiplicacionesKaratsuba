package com.nicolas.taller;

import java.math.BigInteger;

import java.util.Random;

public class MultiplicacionMetodoNormal {

    private BigInteger num1;

    private BigInteger num2;

    public MultiplicacionMetodoNormal(int cantidadDigitos) {

        num1 = generarNumeroRandom(cantidadDigitos);

        num2 = generarNumeroRandom(cantidadDigitos);

    }

    public BigInteger generarNumeroRandom(int cantidadDigitos){

        BigInteger num;
        do{
            Random rnd = new Random();

            StringBuilder sb = new StringBuilder();
            sb.append(rnd.nextInt(9) + 1);

            for (int i = 1; i < cantidadDigitos; i++) {
                sb.append(rnd.nextInt(10));
            }

            num = new BigInteger(sb.toString());

            System.out.println("num1 = " + num);

        }while (num.toString().length() != cantidadDigitos);

        return num;
    }

    public BigInteger multiplicar(){

        BigInteger solucionMetodoTradicional = BigInteger.ZERO;

        int cantidadNumerosNum1 = num1.toString().length();

        int cantidadNumerosNum2 = num2.toString().length();

        for(int i = 0; i < cantidadNumerosNum1; i++){
            BigInteger auxNum2 = num2;

            BigInteger numeroMultiplicando1 = new BigInteger(String.valueOf(num1.toString().charAt(num1.toString().length() - 1)));

            num1 = num1.divide(new BigInteger("10"));

            for(int j = 0; j < cantidadNumerosNum2; j++){
                BigInteger numeroMultiplicando2 = new BigInteger(String.valueOf(auxNum2.toString().charAt(auxNum2.toString().length() - 1)));

                auxNum2 = auxNum2.divide(new BigInteger("10"));

                solucionMetodoTradicional = solucionMetodoTradicional.add(new BigInteger(String.valueOf(new BigInteger("10").pow(j).multiply(new BigInteger("10").pow(i)).multiply(numeroMultiplicando1).multiply(numeroMultiplicando2))));
                
            }

        }

        return solucionMetodoTradicional;

    }

    public void solucionMultiplicacion(){

        long inicioTiempo = System.nanoTime();

        BigInteger solucion = multiplicar();

        long finTiempo = System.nanoTime();

        long duracion = finTiempo - inicioTiempo;

        System.out.println("solucionMetodoTradicional = " + solucion);

        System.out.println("duracion = " + duracion + "ns");
    }
}
