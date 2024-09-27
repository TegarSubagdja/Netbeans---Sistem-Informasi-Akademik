/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kingt
 */
public class Dosen {
    
    private int nid;
    private String nama;
    private String nomor;
    private String email;
    private String alamat;
    private String prodi;

    public Dosen() {
    }

    public Dosen(int nid, String nama, String nomor, String email, String alamat, String prodi) {
        this.nid = nid;
        this.nama = nama;
        this.nomor = nomor;
        this.email = email;
        this.alamat = alamat;
        this.prodi = prodi;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    
    
}
