/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.Akun;
import Model.ConnectionManager;
import Model.Keuangan;
import Model.Mahasiswa;
import Model.Nilai;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kingt
 */
public class ControllerMahasiswa {

    private Akun acc;

    public ControllerMahasiswa() {
    }

    public ControllerMahasiswa(Akun acc) {
        this.acc = acc;
    }

    public Akun checkLogin(String username, String password) {
        String query = "SELECT * FROM login_mhs WHERE username='" + username + "' AND password='" + password + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Akun acc = null; // Inisialisasi dengan null
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                acc = new Akun();
                acc.setNim(rs.getInt("nim"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            // Tangani kesalahan
        }
        conMan.logOff();
        return acc;
    }

    public Mahasiswa getMhs() {
        String query = "SELECT * FROM mahasiswa WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Mahasiswa mhs = new Mahasiswa();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                mhs.setNim(rs.getInt("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setStatus(rs.getString("status"));
                mhs.setDosen_wali(rs.getString("dosen_wali"));
                mhs.setSemester_aktif(rs.getString("semester_aktif"));
                mhs.setBatas_studi(rs.getString("batas_studi"));
                mhs.setEmail(rs.getString("email"));
                mhs.setNomor(rs.getString("nomor"));
                mhs.setProdi(rs.getString("prodi"));
            }
        } catch (SQLException ex) {
        }
        conMan.logOff();
        return mhs;
    }

    public List<Nilai> getNilai() {
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Nilai> listn = new ArrayList<>();

        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Nilai n = new Nilai(); // Memindahkan inisialisasi objek Nilai ke dalam perulangan while
                n.setId(rs.getInt("id"));
                n.setKode(rs.getString("kode"));
                n.setMataKuliah(rs.getString("matakuliah"));
                n.setSks(rs.getString("sks"));
                n.setSemester(rs.getString("semester"));
                n.setNilai(rs.getString("nilai"));
                n.setBobot(rs.getString("bobot"));
                n.setNk(rs.getString("nk"));
                listn.add(n);
            }
        } catch (SQLException ex) {
        }

        conMan.logOff();
        return listn;

    }

    public Keuangan getKeuangan() {
        String query = "SELECT * FROM keuangan_mhs WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Keuangan ku = new Keuangan();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                ku.setDpp_wajib(rs.getInt("dpp_wajib"));
                ku.setUkt(rs.getInt("ukt"));
                ku.setUkv(rs.getInt("ukv"));
                ku.setTanggalJatuhTempoPembayaran(rs.getTimestamp("tanggal_jatuh_tempo_pembayaran"));
                ku.setTanggalJatuhTempoPerwalian(rs.getTimestamp("tanggal_jatuh_tempo_perwalian"));
            }
        } catch (SQLException ex) {
        }
        conMan.logOff();

        // Menghitung denda pembayaran jika terlambat
        LocalDate currentDate = LocalDate.now();
        LocalDate jatuhTempoPembayaran = ku.getTanggalJatuhTempoPembayaran().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dendaPembayaran = 0;
        if (currentDate.isAfter(jatuhTempoPembayaran)) {
            dendaPembayaran = (int) Math.round((ku.getDpp_wajib() + ku.getUkt() + ku.getUkv()) * 0.05);
        }

        // Menghitung denda perwalian jika terlambat
        LocalDate jatuhTempoPerwalian = ku.getTanggalJatuhTempoPerwalian().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int dendaPerwalian = 0;
        if (currentDate.isAfter(jatuhTempoPerwalian)) {
            dendaPerwalian = (int) Math.round((ku.getDpp_wajib() + ku.getUkt() + ku.getUkv()) * 0.05);
        }

        // Menghitung total denda jika terlambat pembayaran dan perwalian
        int totalDenda = dendaPembayaran + dendaPerwalian;

        // Set nilai telat_perwalian dan telat_pembayaran berdasarkan kondisi
        ku.setTelat_perwalian(dendaPerwalian);
        ku.setTelat_pembayaran(dendaPembayaran);
        ku.setTotalDenda(totalDenda);

        return ku;

    }

    public double getIpk() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                double bobot = rs.getDouble("bobot");
                double sks = rs.getInt("sks");
                totalNilaiSks += bobot * sks;
                totalSks += sks;
            }
            if (totalSks > 0) {
                ipk = totalNilaiSks / totalSks;
            }
        } catch (Exception e) {
        }
        return ipk;
    }

    public double getSks() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                double sks = rs.getInt("sks");
                totalSks += sks;
            }
        } catch (Exception e) {
        }
        return totalSks;
    }

    public double getNk() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getNim() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                double bobot = rs.getDouble("bobot");
                double sks = rs.getInt("sks");
                totalNilaiSks += bobot * sks;
            }
        } catch (Exception e) {
        }
        return totalNilaiSks;
    }

}
