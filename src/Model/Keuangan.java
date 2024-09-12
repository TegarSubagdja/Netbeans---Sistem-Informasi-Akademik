/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Control.ControllerMahasiswa;
import java.util.Date;

/**
 *
 * @author kingt
 */
public class Keuangan {

    private int dpp_wajib;
    private int ukt;
    private int biaya;
    private int ukv;
    private int totalKewajiban;
    private Date TanggalJatuhTempoPembayaran;
    private Date TanggalJatuhTempoPerwalian;
    private int Telat_pembayaran;
    private int Telat_perwalian;
    private boolean lunas;
    private double denda;
    private int totalDenda;

    public Keuangan() {
    }

    public Keuangan(int dpp_wajib, int ukt, int biaya, int ukv, int totalKewajiban, Date TanggalJatuhTempoPembayaran, Date TanggalJatuhTempoPerwalian, int Telat_pembayaran, int Telat_perwalian, boolean lunas, double denda, int totalDenda) {
        this.dpp_wajib = dpp_wajib;
        this.ukt = ukt;
        this.biaya = biaya;
        this.ukv = ukv;
        this.totalKewajiban = totalKewajiban;
        this.TanggalJatuhTempoPembayaran = TanggalJatuhTempoPembayaran;
        this.TanggalJatuhTempoPerwalian = TanggalJatuhTempoPerwalian;
        this.Telat_pembayaran = Telat_pembayaran;
        this.Telat_perwalian = Telat_perwalian;
        this.lunas = lunas;
        this.denda = denda;
        this.totalDenda = totalDenda;
    }

    public int getDpp_wajib() {
        return dpp_wajib;
    }

    public void setDpp_wajib(int dpp_wajib) {
        this.dpp_wajib = dpp_wajib;
    }

    public int getUkt() {
        return ukt;
    }

    public void setUkt(int ukt) {
        this.ukt = ukt;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    public int getUkv() {
        return ukv;
    }

    public void setUkv(int ukv) {
        this.ukv = ukv;
    }

    public int getTotalKewajiban() {
        return totalKewajiban;
    }

    public void setTotalKewajiban(int totalKewajiban) {
        this.totalKewajiban = totalKewajiban;
    }

    public Date getTanggalJatuhTempoPembayaran() {
        return TanggalJatuhTempoPembayaran;
    }

    public void setTanggalJatuhTempoPembayaran(Date TanggalJatuhTempoPembayaran) {
        this.TanggalJatuhTempoPembayaran = TanggalJatuhTempoPembayaran;
    }

    public Date getTanggalJatuhTempoPerwalian() {
        return TanggalJatuhTempoPerwalian;
    }

    public void setTanggalJatuhTempoPerwalian(Date TanggalJatuhTempoPerwalian) {
        this.TanggalJatuhTempoPerwalian = TanggalJatuhTempoPerwalian;
    }

    public int getTelat_pembayaran() {
        return Telat_pembayaran;
    }

    public void setTelat_pembayaran(int Telat_pembayaran) {
        this.Telat_pembayaran = Telat_pembayaran;
    }

    public int getTelat_perwalian() {
        return Telat_perwalian;
    }

    public void setTelat_perwalian(int Telat_perwalian) {
        this.Telat_perwalian = Telat_perwalian;
    }

    public boolean isLunas() {
        return lunas;
    }

    public void setLunas(boolean lunas) {
        this.lunas = lunas;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    public int getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(int totalDenda) {
        this.totalDenda = totalDenda;
    }
}
