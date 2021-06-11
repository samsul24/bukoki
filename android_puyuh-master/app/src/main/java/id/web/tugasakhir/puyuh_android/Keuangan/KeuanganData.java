package id.web.tugasakhir.puyuh_android.Keuangan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class KeuanganData {
    @SerializedName("id")
    @Expose
    private Integer idKeuangan;

    @SerializedName("total_kas")
    @Expose
    private Integer totalKas;

    @SerializedName("tanggal_kas")
    @Expose
    private String tanggalKas;

    @SerializedName("jumlah_gaji")
    @Expose
    private Integer jumlahGaji;

    @SerializedName("tanggal_gaji")
    @Expose
    private String tanggalGaji;

    public KeuanganData(Integer idKeuangan, Integer totalKas, String tanggalKas, Integer jumlahGaji, String tanggalGaji) {
        this.idKeuangan = idKeuangan;
        this.totalKas = totalKas;
        this.tanggalKas = tanggalKas;
        this.jumlahGaji = jumlahGaji;
        this.tanggalGaji = tanggalGaji;
    }

    public Integer getIdKeuangan() {
        return idKeuangan;
    }

    public void setIdKeuangan(Integer idKeuangan) {
        this.idKeuangan = idKeuangan;
    }

    public Integer getTotalKas() {
        return totalKas;
    }

    public void setTotalKas(Integer totalKas) {
        this.totalKas = totalKas;
    }

    public String getTanggalKas() {
        return tanggalKas;
    }

    public void setTanggalKas(String tanggalKas) {
        this.tanggalKas = tanggalKas;
    }

    public Integer getJumlahGaji() {
        return jumlahGaji;
    }

    public void setJumlahGaji(Integer jumlahGaji) {
        this.jumlahGaji = jumlahGaji;
    }

    public String getTanggalGaji() {
        return tanggalGaji;
    }

    public void setTanggalGaji(String tanggalGaji) {
        this.tanggalGaji = tanggalGaji;
    }
}
