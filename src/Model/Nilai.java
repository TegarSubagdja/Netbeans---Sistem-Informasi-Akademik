/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kingt
 */
public class Nilai {
    private String kode;
    private String mataKuliah;
    private String sks;
    private String semester;
    private String nilai;
    private String bobot;
    private String nk;

    public Nilai() {
    }

    public Nilai(String kode, String mataKuliah, String sks, String semester, String nilai, String bobot, String nk) {
        this.kode = kode;
        this.mataKuliah = mataKuliah;
        this.sks = sks;
        this.semester = semester;
        this.nilai = nilai;
        this.bobot = bobot;
        this.nk = nk;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getNilai() {
        return nilai;
    }

    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }

    public String getNk() {
        return nk;
    }

    public void setNk(String nk) {
        this.nk = nk;
    }

    
}
