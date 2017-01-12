package tenqube.com.qlip_android.ui_kj.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;

import tenqube.com.qlip_android.R;
import tenqube.com.qlip_android.constans.Constants;
import tenqube.com.qlip_android.custom_view.BackPressEditText;
import tenqube.com.qlip_android.data.TagTableData;
import tenqube.com.qlip_android.util.ValidationUtil;


public class TagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public ArrayList<TagTableData> hashTagList = new ArrayList<>();
    public final static int VIEW_TYPE_PLUS=0;
    public final static int VIEW_TYPE_NORMAL=1;
    private Context mContext;
    private boolean isTouch;
    private DetailTranPresenter mDetailTranPresenter;
    private InputMethodManager imm;
    public boolean tagFlag = false;

    public TagAdapter(Context context, DetailTranPresenter detailTranPresenter) {
        mContext = context;
        this.mDetailTranPresenter = detailTranPresenter;
        imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    public void addAll(ArrayList<TagTableData> hashTagList){
        this.hashTagList = hashTagList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if(position==hashTagList.size()){
            return VIEW_TYPE_PLUS;
        }else{
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return hashTagList.size()+1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewType==VIEW_TYPE_PLUS){
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_tran_plus_tag_item, viewGroup, false);
            return new PlusViewHolder(vFirst);
        }else{
            ViewGroup vFirst = (ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_tran_tag_item, viewGroup, false);
            return new HashTagViewHolder(vFirst);
        }
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HashTagViewHolder){
            final HashTagViewHolder cHolder = (HashTagViewHolder) holder;
            cHolder.dimView.setVisibility(hashTagList.get(position).isShown? View.GONE: View.VISIBLE);
            cHolder.tagTextView.setText(hashTagList.get(position).tagName);
        }
    }




    public class PlusViewHolder extends  RecyclerView.ViewHolder implements  BackPressEditText.OnBackPressListener{
        FrameLayout addContainer;
        FrameLayout inputContainer;
        BackPressEditText hashTagNameEditText;
        public PlusViewHolder(View view){
            super(view);
            addContainer = (FrameLayout) view.findViewById(R.id.add_container);
            addContainer.setVisibility(View.VISIBLE);
            inputContainer = (FrameLayout)view.findViewById(R.id.input_container);
            inputContainer.setVisibility(View.GONE);
            hashTagNameEditText = (BackPressEditText)view.findViewById(R.id.hash_tag_name);
            hashTagNameEditText.setOnBackPressListener(this);
            if(hashTagNameEditText != null)hashTagNameEditText.setFilters(new InputFilter[]{ValidationUtil.filter,new InputFilter.LengthFilter(20)});

            addContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagFlag = true;
                    addContainer.setVisibility(View.GONE);
                    inputContainer.setVisibility(View.VISIBLE);
                    hashTagNameEditText.requestFocus();
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                }
            });

            hashTagNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if(actionId == EditorInfo.IME_ACTION_DONE){
                        boolean flag=false;
                        if("".equals(hashTagNameEditText.getText().toString().replace("\n","").replace(" ","").trim())){
                            Toast.makeText(mContext,"태그 이름을 지정해 주세요.", Toast.LENGTH_SHORT).show();
                            return  true;
                        }else{
                            for(TagTableData model:hashTagList){
                                if(model.tagName.equals(hashTagNameEditText.getText().toString().trim())){
                                    flag=true;
                                    break;
                                }
                            }
                            if(!flag){
                                if(!isTouch){
                                    isTouch=true;
                                    final String tagName=hashTagNameEditText.getText().toString().trim();
                                    TagTableData model=new TagTableData();
                                    model.tagName=tagName;
                                    model.mainType=1;
                                    model.tagId=mDetailTranPresenter.insertHashTagName(tagName);
                                    hashTagList.add(model);
                                    Constants.refresh = true;
                                    notifyDataSetChanged();
                                    isTouch=false;

                                }
                            }else{
                                Toast.makeText(mContext, mContext.getString(R.string.already_same_name), Toast.LENGTH_SHORT).show();
                                return  true;
                            }
                        }
                        inputContainer.setVisibility(View.GONE);
                        addContainer.setVisibility(View.VISIBLE);
                        imm.hideSoftInputFromWindow(hashTagNameEditText.getWindowToken(), 0);
                        hashTagNameEditText.setText("");
                    }
                    return false;
                }
            });
        }

        @Override
        public void onBackPress() {
            hashTagNameEditText.setText("");
            inputContainer.setVisibility(View.GONE);
            addContainer.setVisibility(View.VISIBLE);
            imm.hideSoftInputFromWindow(hashTagNameEditText.getWindowToken(), 0);
        }
    }
    public  class HashTagViewHolder extends RecyclerView.ViewHolder  {
        FrameLayout containerFrameLayout;
        FrameLayout dimView;
        TextView tagTextView;

        public HashTagViewHolder(View convertView) {
            super(convertView);
            containerFrameLayout = (FrameLayout) convertView.findViewById(R.id.container);
            containerFrameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition() != -1){
                        tagFlag = true;
                        hashTagList.get(getAdapterPosition()).isShown = !hashTagList.get(getAdapterPosition()).isShown;
                        notifyDataSetChanged();
                    }
                }
            });
            dimView = (FrameLayout) convertView.findViewById(R.id.dim);
            tagTextView = (TextView) convertView.findViewById(R.id.tag);

        }
    }


}