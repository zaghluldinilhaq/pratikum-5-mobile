import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterPelanggan extends RecyclerView.Adapter<AdapterPelanggan.ViewHolder> {
    private Context context;
    private List<ModelPelanggan> pelangganList;

    public AdapterPelanggan(Context context, List<ModelPelanggan> pelangganList) {
        this.context = context;
        this.pelangganList = pelangganList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pelanggan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelPelanggan pelanggan = pelangganList.get(position);
        holder.nama.setText(pelanggan.getNama());
        holder.alamat.setText(pelanggan.getAlamat());
    }

    @Override
    public int getItemCount() {
        return pelangganList.size();
    }

    public void clear() {
        pelangganList.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, alamat;

        public ViewHolder(View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama);
            alamat = itemView.findViewById(R.id.alamat);
        }
    }
}
