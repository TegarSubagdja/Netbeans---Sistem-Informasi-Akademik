/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Akun;
import Model.ConnectionManager;
import Model.Dosen;
import Model.Mahasiswa;
import Model.Matakuliah;
import Model.Nilai;
import View.Index_Mahasiswa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kingt
 */
public class ControllerDosen {

    private Akun acc;

    public ControllerDosen() {
    }

    public ControllerDosen(Akun acc) {
        this.acc = acc;
    }

    public List<Nilai> getNilai(String kode) {
        String query = "SELECT * FROM nilai_mhs WHERE kode='" + kode + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Nilai> listn = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Nilai n = new Nilai();
                n.setNim(rs.getInt("nim"));
                n.setKode(rs.getString("kode"));
                n.setMataKuliah(rs.getString("matakuliah"));
                n.setSks(rs.getString("sks"));
                n.setSemester(rs.getString("semester"));
                n.setNilai(rs.getString("nilai"));
                n.setBobot(rs.getString("bobot"));
                n.setNk(rs.getString("nk"));
                listn.add(n);
            }
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listn;
    }

    public List<Mahasiswa> getMhs() {
        String query = "SELECT * FROM mahasiswa";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Mahasiswa> listmhs = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNim(rs.getInt("nim"));
                mhs.setNama(rs.getString("nama"));
                mhs.setStatus(rs.getString("status"));
                mhs.setDosen_wali(rs.getString("dosen_wali"));
                mhs.setSemester_aktif(rs.getString("semester_aktif"));
                mhs.setBatas_studi(rs.getString("batas_studi"));
                mhs.setEmail(rs.getString("email"));
                mhs.setNomor(rs.getString("nomor"));
                mhs.setProdi(rs.getString("prodi"));
                listmhs.add(mhs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listmhs;
    }

    public List<Matakuliah> getMatakuliah() {
        String query = "SELECT * FROM Matakuliah";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Matakuliah> listMk = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Matakuliah mk = new Matakuliah(); // Memindahkan inisialisasi objek Nilai ke dalam perulangan while
                mk.setKode(rs.getString("kode"));
                mk.setMatakuliah(rs.getString("matakuliah"));
                mk.setSks(rs.getInt("sks"));
                mk.setKelas(rs.getString("kelas"));
                mk.setJenis(rs.getString("jenis"));
                mk.setSisa(rs.getString("sisa"));
                mk.setStatus_awal(rs.getString("status_awal"));
                mk.setBdatm(rs.getString("bdatm"));
                mk.setApprove_wali(rs.getString("Approve_wali"));
                mk.setHari(rs.getString("hari"));
                mk.setJam(rs.getString("jam"));
                listMk.add(mk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (listMk.isEmpty()) {
            System.out.println("Kosong");
        }
        conMan.logOff();
        return listMk;
    }

    public void updateNilai(int nim, String kode, String nilai, int sks) {
        double bobot = 0;
        nilai = nilai.toUpperCase();
        if (nilai.equals("A")) {
            bobot = 4.0;
        } else if (nilai.equals("AB")) {
            bobot = 3.5;
        } else if (nilai.equals("B")) {
            bobot = 3.0;
        } else if (nilai.equals("C")) {
            bobot = 2.0;
        } else if (nilai.equals("D")) {
            bobot = 1.0;
        } else if (nilai.equals("E")) {
            bobot = 0;
        }
        double nk = sks * bobot;
        String query = "UPDATE nilai_mhs SET nilai='" + nilai + "', bobot='" + bobot + "', nk='" + nk + "' WHERE nim='" + nim + "' AND kode='" + kode + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
    }

    public Dosen getDosen() {
        String query = "SELECT * FROM Dosen WHERE nid='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Dosen ds = new Dosen();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                ds.setNid(rs.getInt("nid"));
                ds.setNama(rs.getString("nama"));
                ds.setNomor(rs.getString("nomor"));
                ds.setAlamat(rs.getString("alamat"));
                ds.setEmail(rs.getString("email"));
                ds.setProdi(rs.getString("prodi"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return ds;
    }

    public void updateDataDosen(String field, String input) {
        String query = "UPDATE Dosen SET " + field + " = '" + input + "' WHERE nid = '" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
    }

    public void hapusDosen(Dosen ds) {
        String query = "DELETE FROM Dosen WHERE nid='" + ds.getNid() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
    }

    public void hapusAkunDosen(Dosen ds) {
        String query = "DELETE FROM Dosen WHERE nid='" + ds.getNid() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
    }
}
