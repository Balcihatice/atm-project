package atmproject;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    //account:hesap

    private int accoundId;//hesap no
    private int pinNumber; //sifre


    private double chekingBalance;//cheking icin bakiyemiz >> vadesiz hesap
    private double savingBalance;//saving icin bakiyemiz >> vadeli hesap bakiyemiz
    //Dolari daha guzel formata donusturduk


    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    //Son bakiyeyi goster                           suanki bakiye
    public void displayCurrentAmount(double balance) {
        System.out.println("Hesabinizda bulunan bakiye : " + moneyFormat.format(balance));
    }


    Scanner input = new Scanner(System.in);

    public int getAccoundId() {
        return accoundId;
    }

    public void setAccoundId(int accoundId) {
        this.accoundId = accoundId;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public double getChekingBalance() {
        return chekingBalance;
    }

    public void setChekingBalance(double chekingBalance) {
        this.chekingBalance = chekingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
    }

    //para cekme
    //doubleparaCekmeIslemindenSonraKalanMiktar =>calculateChekingBalanceAfterWithdraw
    //amount => miktar
    private double calculateChekingBalanceAfterWithdraw(double amount) {

        //100$
        chekingBalance = chekingBalance - amount;
        return chekingBalance;

    }

    //Para yatirma isleminden sonra kalan bakiyeyi hesapla       kisinin verdigi para:amount
    private double calculateChekingBalanceAfterDeposit(double amount) {

        chekingBalance = chekingBalance + amount;
        return chekingBalance;
    }

    //para cekme : saving hesabindan para cekildikten sonra kalan bakiye

    private double calculateSavingBalanceAfterWithdraw(double amount) {
        //vadeli hesap bakiyesi
        savingBalance = savingBalance - amount;
        return savingBalance;
    }

    //para yatirma : saving hesabina para yatirdiktan sonra kalan bakiyeyi hesaplayiniz
    private double calculateSavingBalanceAfterDeposit(double amount) {

        savingBalance = savingBalance + amount;
        return savingBalance;
    }

    //Musteri ile para cekmek icin etkilesime gecelim >. cheking hesabindan para cekme
    public void getChekingWithdraw() {
        displayCurrentAmount(chekingBalance);//bakiyeyi gosterdik
        System.out.println("Gekmek istediginiz miktari giriniz");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getChekingWithdraw();//Kisi hatali girerse bulunndugu methodu tekrar cagirip dogru rakami girmesi icin sans verdik
            //recursive method > kendisini tekrar cagirma
        } else if (amount <= chekingBalance) {
            calculateChekingBalanceAfterWithdraw(amount); //Para cekebilirsiniz methodunu cagirdik
            displayCurrentAmount(chekingBalance); //Kalan bakiye
        } else {
            System.out.println("Yetersiz bakiye");
        }
    }

    //Para yatirmak icin Musteri ile etkilesime gecelim
    public void getChekingDeposit() {
        displayCurrentAmount(chekingBalance);
        System.out.println("Yatirmak istediginiz miktari giriniz");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getChekingDeposit();

        } else {
            calculateChekingBalanceAfterDeposit(amount);
            System.out.println();
            displayCurrentAmount(chekingBalance); //kalan bakiyeyi gosterdik
        }


    }


    //Ayni cekme yatirma islemlerini savingAccount icin yapmaliyiz

    public void getSavingWithdraw() {
        displayCurrentAmount(savingBalance);//bakiyeyi gosterdik
        System.out.println("Yatirmak istediginiz miktari giriniz");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getSavingWithdraw();//Hatali giris yaparsa islem hakki tanidik
        } else if (amount <= savingBalance) {
            calculateSavingBalanceAfterWithdraw(amount);
            displayCurrentAmount(savingBalance);
        }else{
            System.out.println("Yetersiz Bakiye");
        }
    }


    //Kendi hesabina para yatirma(savingAccount)

    public void getSavingDeposit() {
        displayCurrentAmount(savingBalance);//bakiyeyi gosterdik
        System.out.println("Gekmek istediginiz miktari giriniz");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Sifir veya eksi rakamlar gecersizdir!");
            getSavingDeposit();
        }else{
            calculateSavingBalanceAfterWithdraw(amount);
            displayCurrentAmount(savingBalance);
        }
    }
}