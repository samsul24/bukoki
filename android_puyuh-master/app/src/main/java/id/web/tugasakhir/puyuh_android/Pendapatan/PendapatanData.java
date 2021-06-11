package id.web.tugasakhir.puyuh_android.Pendapatan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PendapatanData {
    @SerializedName("id")
    @Expose
    private Integer idPendapatan;

    @SerializedName("id_user")
    @Expose
    private Integer idUser;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;

    @SerializedName("harga")
    @Expose
    private Integer hargaPendapatan;

    @SerializedName("jumlah")
    @Expose
    private Integer jumlahPendapatan;

    @SerializedName("satuan")
    @Expose
    private String satuanPendapatan;

    @SerializedName("total")
    @Expose
    private Integer totalPendapatan;

    public PendapatanData(Integer idPendapatan, Integer idUser, String tanggal,  Integer hargaPendapatan, Integer jumlahPendapatan, String satuanPendapatan, Integer totalPendapatan) {
        this.idPendapatan = idPendapatan;
        this.idUser = idUser;
        this.tanggal = tanggal;
        this.hargaPendapatan = hargaPendapatan;
        this.jumlahPendapatan = jumlahPendapatan;
        this.satuanPendapatan = satuanPendapatan;
        this.totalPendapatan = totalPendapatan;
    }

    public Integer getIdPendapatan() {
        return idPendapatan;
    }

    public void setIdPendapatan(Integer idPendapatan) {
        this.idPendapatan = idPendapatan;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public Integer getHargaPendapatan() {
        return hargaPendapatan;
    }

    public void setHargaPendapatan(Integer hargaPendapatan) {
        this.hargaPendapatan = hargaPendapatan;
    }

    public Integer getJumlahPendapatan() {
        return jumlahPendapatan;
    }

    public void setJumlahPendapatan(Integer jumlahPendapatan) {
        this.jumlahPendapatan = jumlahPendapatan;
    }

    public String getSatuanPendapatan() {
        return satuanPendapatan;
    }

    public void setSatuanPendapatan(String satuanPendapatan) {
        this.satuanPendapatan = satuanPendapatan;
    }

    public Integer getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(Integer totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }
}