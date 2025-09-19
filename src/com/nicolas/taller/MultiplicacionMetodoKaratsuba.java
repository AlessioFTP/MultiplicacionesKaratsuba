package com.nicolas.taller;

import java.math.BigInteger;

import java.util.Random;

public class MultiplicacionMetodoKaratsuba {

    private BigInteger num1;

    private BigInteger num2;

    public MultiplicacionMetodoKaratsuba(int cantidadDigitos) {

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

            System.out.println("num = " + num);

        }while (num.toString().length() != cantidadDigitos);

        return num;
    }

    public BigInteger multiplicarKaratsuba(BigInteger num1, BigInteger num2) {

        if (num1.equals(BigInteger.ZERO) || num2.equals(BigInteger.ZERO)) {
            return BigInteger.ZERO;
        }

        int umbralBits = 8;

        if (num1.bitLength() <= umbralBits || num2.bitLength() <= umbralBits) {
            return num1.multiply(num2);
        }

        int tamanio = Math.max(num1.bitLength(), num2.bitLength());

        int mitad = tamanio / 2;

        BigInteger x0 = num1.shiftRight(mitad);

        BigInteger x1 = num1.subtract(x0.shiftLeft(mitad));

        BigInteger y0 = num2.shiftRight(mitad);

        BigInteger y1 = num2.subtract(y0.shiftLeft(mitad));

        BigInteger z0 = multiplicarKaratsuba(x0, y0);

        BigInteger z2 = multiplicarKaratsuba(x1, y1);

        BigInteger zSuma = multiplicarKaratsuba(x1.add(x0), y1.add(y0));

        BigInteger z1 = zSuma.subtract(z2).subtract(z0);

        return z2.shiftLeft(2 * mitad).add(z1.shiftLeft(mitad)).add(z0);
    }

    public void solucionMultiplicacion(){
        long inicioTiempo = System.nanoTime();

        BigInteger solucion = multiplicarKaratsuba(num1, num2);

        long finTiempo = System.nanoTime();

        long duracion = finTiempo - inicioTiempo;

        System.out.println("solucionMetodoKaratsuba = " + solucion);

        System.out.println("duracion = " + duracion + "ns");
    }
}
