package id.web.tugasakhir.puyuh_android.LaporanHarian;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.web.tugasakhir.puyuh_android.R;

public class LaporanAdapter extends RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder> {
    private List<LaporanData> dataList;
    private Context context;

    public LaporanAdapter(List<LaporanData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    class LaporanViewHolder extends RecyclerView.ViewHolder{
        public TextView mTanggalLaporan, mIdKandang, mJumlahTelur, mJumlahKematian;

        LaporanViewHolder(View itemView) {
            super(itemView);
            mTanggalLaporan = itemView.findViewById(R.id.textTanggalLaporanList);
            mIdKandang = itemView.findViewById(R.id.textIdKandangLaporan);
            mJumlahTelur = itemView.findViewById(R.id.textTelurLaporan);
            mJumlahKematian = itemView.findViewById(R.id.textKematianLaporan);

        }
    }

    @Override
    public LaporanViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.laporan_row, parent, false);
        return new LaporanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LaporanViewHolder holder, int position) {
        holder.mTanggalLaporan.setText("Tanggal : " +dataList.get(position).getTanggalLaporan());
        holder.mIdKandang.setText("No Kandang : "+dataList.get(position).getNoKandangLaporan());
        holder.mJumlahTelur.setText("Jumlah Telur : "+dataList.get(position).getJumlahTelur()+" Butir");
        holder.mJumlahKematian.setText("jumlah kematian : "+dataList.get(position).getJumlahKematian()+" Ekor");

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
