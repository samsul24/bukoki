package id.web.tugasakhir.puyuh_android.Keuangan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.web.tugasakhir.puyuh_android.R;

public class KeuanganAdapter extends RecyclerView.Adapter<KeuanganAdapter.KeuanganViewHolder> {
    private List<KeuanganData> dataList;
    private Context context;

    public KeuanganAdapter(List<KeuanganData> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    class KeuanganViewHolder extends RecyclerView.ViewHolder{
        public TextView mTanggalKas, mTextKas, mTanggalGaji, mTextGaji;

        KeuanganViewHolder(View itemView) {
            super(itemView);
            mTanggalKas = itemView.findViewById(R.id.TanggalKas);
            mTextKas = itemView.findViewById(R.id.textKas);
            mTanggalGaji = itemView.findViewById(R.id.TanggalGaji);
            mTextGaji = itemView.findViewById(R.id.textGaji);

        }
    }

    @Override
    public KeuanganViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.keuangan_row, parent, false);
        return new KeuanganViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeuanganViewHolder holder, int position) {
        holder.mTextKas.setText("Kas : Rp. "+ dataList.get(position).getTotalKas());
        holder.mTanggalGaji.setText("Tanggal : " + dataList.get(position).getTanggalGaji());
        holder.mTextGaji.setText("Gaji : Rp. "+ dataList.get(position).getJumlahGaji() );

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

