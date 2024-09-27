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
import Model.Matakuliah;
import Model.Nilai;
import Model.Perwalian;
import View.Index_Mahasiswa;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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

    public Mahasiswa getMhs() {
        String query = "SELECT * FROM mahasiswa WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Mahasiswa mhs = new Mahasiswa();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
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
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return mhs;
    }

    public List<Nilai> getNilai() {
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Nilai> listn = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Nilai n = new Nilai();
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

    public List<Matakuliah> getMatakuliah() {
        String query = "SELECT * FROM Matakuliah";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Matakuliah> listMk = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Matakuliah n = new Matakuliah(); // Memindahkan inisialisasi objek Nilai ke dalam perulangan while
                n.setKode(rs.getString("kode"));
                n.setMatakuliah(rs.getString("matakuliah"));
                n.setSks(rs.getInt("sks"));
                n.setKelas(rs.getString("kelas"));
                n.setJenis(rs.getString("jenis"));
                n.setSisa(rs.getString("sisa"));
                n.setStatus_awal(rs.getString("status_awal"));
                n.setBdatm(rs.getString("bdatm"));
                n.setApprove_wali(rs.getString("Approve_wali"));
                n.setHari(rs.getString("hari"));
                n.setJam(rs.getString("jam"));
                listMk.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listMk;
    }

    public List<Matakuliah> getMatakuliah(String status) {
        String query = "SELECT * FROM Matakuliah WHERE status_awal='" + status + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        List<Matakuliah> listMk = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                Matakuliah n = new Matakuliah();
                n.setKode(rs.getString("kode"));
                n.setMatakuliah(rs.getString("matakuliah"));
                n.setSks(rs.getInt("sks"));
                n.setKelas(rs.getString("kelas"));
                n.setJenis(rs.getString("jenis"));
                n.setSisa(rs.getString("sisa"));
                n.setStatus_awal(rs.getString("status_awal"));
                n.setBdatm(rs.getString("bdatm"));
                n.setApprove_wali(rs.getString("Approve_wali"));
                n.setHari(rs.getString("hari"));
                n.setJam(rs.getString("jam"));
                listMk.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return listMk;
    }

    public void updateKeuangan(double ukv, double total) {
        String query = "UPDATE keuangan_mhs SET ukv='" + ukv + "', total='" + total + "' WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
    }

    public Keuangan getKeuangan() {
        double denda = 0;
        String query = "SELECT * FROM keuangan_mhs WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Keuangan ku = new Keuangan();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                ku.setDpp_wajib(rs.getInt("dpp_wajib"));
                ku.setUkt(rs.getInt("ukt"));
                ku.setBiaya(rs.getInt("biayaSks"));
                ku.setUkv(rs.getInt("ukv"));
                ku.setTanggalJatuhTempoPembayaran(rs.getTimestamp("tanggal_jatuh_tempo_pembayaran"));
                ku.setTanggalJatuhTempoPerwalian(rs.getTimestamp("tanggal_jatuh_tempo_perwalian"));
                ku.setLunas(rs.getBoolean("lunas"));
                denda = rs.getDouble("denda");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();

        int dendaPembayaran = 0;
        if (!ku.isLunas()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate jatuhTempoPembayaran = ku.getTanggalJatuhTempoPembayaran().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (currentDate.isAfter(jatuhTempoPembayaran)) {
                dendaPembayaran = (int) Math.round((ku.getDpp_wajib() + ku.getUkt() + ku.getUkv()) * denda);
            }
        }

        int dendaPerwalian = 0;
        if (!ku.isLunas()) {
            LocalDate currentDate = LocalDate.now();
            LocalDate jatuhTempoPerwalian = ku.getTanggalJatuhTempoPerwalian().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (currentDate.isAfter(jatuhTempoPerwalian)) {
                dendaPerwalian = (int) Math.round((ku.getDpp_wajib() + ku.getUkt() + ku.getUkv()) * denda);
            }
        }
        
        int totalDenda = dendaPembayaran + dendaPerwalian;
        ku.setTelat_perwalian(dendaPerwalian);
        ku.setTelat_pembayaran(dendaPembayaran);
        ku.setTotalDenda(totalDenda);
        return ku;
    }

    public double getIpk() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getId() + "'";
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
                ipk = Math.ceil(ipk * 100) / 100;
            }
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }

        DecimalFormat df = new DecimalFormat("#0.00");
        String formattedIpk = df.format(ipk);

        return Double.parseDouble(formattedIpk);
    }

    public double maxSks() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getId() + "'";
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
                ipk = Math.ceil(ipk * 100) / 100; // Bulatkan IPK ke dua angka setelah koma (bulat atas)
            }
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ipk <= 3.0) {
            return 20.0;
        } else {
            return 24.0;
        }
    }

    public double getSks() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                double sks = rs.getInt("sks");
                totalSks += sks;
            }
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalSks;
    }

    public double getNk() {
        double ipk = 0.0;
        int totalSks = 0;
        double totalNilaiSks = 0.0;
        String query = "SELECT * FROM nilai_mhs WHERE nim='" + acc.getId() + "'";
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
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalNilaiSks;
    }

    public int updateDataMahasiswa(String field, String value) {
        int hasil = 0;
        String query = "UPDATE Mahasiswa SET " + field + " = '" + value + "' WHERE nim = '" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Statement stm = null;
        try {
            stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }

    public Perwalian getPerwalian() {
        String query = "SELECT * FROM perwalian_mhs WHERE nim='" + acc.getId() + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        Perwalian pw = new Perwalian();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                pw.setSks(rs.getInt("sks"));
                pw.setMk(rs.getInt("mk"));
                pw.tanggal_update(rs.getDate("tanggal_update"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pw;
    }

    public int tambahSks(int sks) {
        int hasil = 0;
        String query = "UPDATE perwalian_mhs SET sks='" + sks + "', mk=(mk+1), tanggal_update=NOW() WHERE nim=" + acc.getId();
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public int kurangSks(int sks) {
        int hasil = 0;
        String query = "UPDATE perwalian_mhs SET sks='" + sks + "', mk=(mk-1), tanggal_update=NOW() WHERE nim=" + acc.getId();
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
        } catch (Exception ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }

    public int tambahMk(String kode) {
        int hasil = 0;
        String query = "UPDATE matakuliah SET status_awal='Ambil', sisa=(sisa-1) WHERE kode='" + kode + "' AND sisa > 0";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
            if (hasil > 0) {
                hasil = 1; // Set hasil menjadi 1 jika berhasil melakukan pengurangan
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }

    public int kurangMk(String kode) {
        int hasil = 0;
        String query = "UPDATE matakuliah SET status_awal='Batal', sisa=(sisa+1) WHERE kode='" + kode + "' AND sisa > 0";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            hasil = stm.executeUpdate(query);
            if (hasil > 0) {
                hasil = 1;
            }
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return hasil;
    }

    public boolean cekMatkul(String kode) {
        boolean sudahAmbil = false;
        String query = "SELECT * FROM matakuliah WHERE kode='" + kode + "' AND status_awal='Ambil'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                sudahAmbil = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sudahAmbil;
    }

    public boolean cekSisa(String kode) {
        boolean adaSisa = false;
        String query = "SELECT * FROM matakuliah WHERE kode='" + kode + "'";
        ConnectionManager conMan = new ConnectionManager();
        Connection conn = conMan.logOn();
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                int sisa = rs.getInt("sisa");
                if (sisa == 0) {
                    adaSisa = false;
                } else {
                    adaSisa = true;
                }
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(Index_Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
        }
        conMan.logOff();
        return adaSisa;
    }

    public int totalKewajiban() {
        int sks = (int) getSks();
        Keuangan b = getKeuangan();
        int totalKewajiban = (sks * b.getBiaya()) + b.getTelat_perwalian() + b.getTelat_pembayaran();
        return totalKewajiban;
    }
}
