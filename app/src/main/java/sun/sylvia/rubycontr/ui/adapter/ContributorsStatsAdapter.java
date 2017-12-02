package sun.sylvia.rubycontr.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import sun.sylvia.rubycontr.R;
import sun.sylvia.rubycontr.github.ContributorStats;
import sun.sylvia.rubycontr.ui.RubyconContract;

import static java.lang.String.format;

public class ContributorsStatsAdapter extends RecyclerView.Adapter<ContributorsStatsAdapter.ViewHolder> {

    private List<ContributorStats> mDataList;
    private RubyconContract.Presenter mPresenter;
    private Context mContext;

    public ContributorsStatsAdapter(List<ContributorStats> dataList, RubyconContract.Presenter presenter) {
        mDataList = dataList;
        mPresenter = presenter;
    }

    public void updateData(List<ContributorStats> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public ContributorsStatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_contributor, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContributorsStatsAdapter.ViewHolder holder, int position) {

        ContributorStats contributorStats = mDataList.get(position);

        holder.contributorLoginTv.setText(contributorStats.getAuthor().getLogin());
        holder.contributorCommitCountTv.setText(format(Locale.UK,"Total commits : %d", contributorStats.getTotal()));
        Picasso.with(mContext)
                .load(contributorStats.getAuthor().getAvatar_url())
                .placeholder(R.drawable.default_thumb)
                .into(holder.contributorProfileImage);

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contributor_login) TextView contributorLoginTv;
        @BindView(R.id.contributor_commit_count) TextView contributorCommitCountTv;
        @BindView(R.id.contributor_profile_image) ImageView contributorProfileImage;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
