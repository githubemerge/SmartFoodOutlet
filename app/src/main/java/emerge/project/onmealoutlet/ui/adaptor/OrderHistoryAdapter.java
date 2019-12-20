package emerge.project.onmealoutlet.ui.adaptor;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenter;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenterImpli;
import emerge.project.onmealoutlet.ui.activity.menus.MenusView;


import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.OrderHistoryEntitte;
import emerge.project.onmealoutlet.utils.entittes.OrderMenuHistory;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<OrderHistoryEntitte> ordersItems;


    int status = 0;

    public OrderHistoryAdapter(Context mContext, ArrayList<OrderHistoryEntitte> item) {
        this.mContext = mContext;
        this.ordersItems = item;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_history, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final OrderHistoryEntitte ordersObject = ordersItems.get(position);

        if(ordersObject.getOrderID()==0){
            holder.textviewOrder.setVisibility(View.GONE);
            holder.textOrdernumber.setVisibility(View.GONE);
        }else {
            holder.textviewOrder.setVisibility(View.VISIBLE);
            holder.textOrdernumber.setVisibility(View.VISIBLE);
            holder.textOrdernumber.setText(String.valueOf(ordersObject.getOrderID()));
        }


        if(ordersObject.getOrderDate()==null){
            holder.textviewDate.setVisibility(View.GONE);
            holder.textOrderdate.setVisibility(View.GONE);
        }else {
            holder.textviewDate.setVisibility(View.VISIBLE);
            holder.textOrderdate.setVisibility(View.VISIBLE);
            holder.textOrderdate.setText(ordersObject.getOrderDate().substring(0,10));
        }


        if(ordersObject.getDispatchType()==null){
            holder.textviewDispatch.setVisibility(View.GONE);
            holder.textDispatchtype.setVisibility(View.GONE);
        }else {
            holder.textviewDispatch.setVisibility(View.VISIBLE);
            holder.textDispatchtype.setVisibility(View.VISIBLE);

            if(String.valueOf(ordersObject.getDispatchType()).equals("D")){
                holder.textDispatchtype.setText("Delivery");
            }else if(String.valueOf(ordersObject.getDispatchType()).equals("P")){
                holder.textDispatchtype.setText("Pickup");
            }else if(String.valueOf(ordersObject.getDispatchType()).equals("T")){
                holder.textDispatchtype.setText("Dine In");
            }


        }


        if(ordersObject.getPaymentTypeCode()==null){
            holder.textviewPayment.setVisibility(View.GONE);
            holder.textPaymenttype.setVisibility(View.GONE);
        }else {
            holder.textviewPayment.setVisibility(View.VISIBLE);
            holder.textPaymenttype.setVisibility(View.VISIBLE);
            holder.textPaymenttype.setText(ordersObject.getPaymentTypeCode());
        }

         if(ordersObject.getOrderMenuHistory()==null){
             holder.recyclerViewFoodDetails.setVisibility(View.GONE);
         }else {
             if(ordersObject.getOrderMenuHistory().isEmpty()){
                 holder.recyclerViewFoodDetails.setVisibility(View.GONE);

             }else {
                 holder.recyclerViewFoodDetails.setVisibility(View.VISIBLE);
                 holder.textPaymenttype.setText(ordersObject.getPaymentTypeCode());
                 setSubItems(mContext,holder,ordersObject.getOrderMenuHistory());

             }
         }





    }

    public void setSubItems(Context context, MyViewHolder holder, ArrayList<OrderMenuHistory> foodist) {


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerViewFoodDetails.setLayoutManager(layoutManager);
        holder.recyclerViewFoodDetails.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerViewFoodDetails.setNestedScrollingEnabled(true);


        OrderHistoryDetialsAdapter orderHistoryDetialsAdapter = new OrderHistoryDetialsAdapter(context, foodist);
        holder.recyclerViewFoodDetails.setAdapter(orderHistoryDetialsAdapter);

    }

    @Override
    public int getItemCount() {
        return ordersItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview1)
        TextView textviewOrder;
        @BindView(R.id.textView_ordernumber)
        TextView textOrdernumber;


        @BindView(R.id.textview2)
        TextView textviewDate;
        @BindView(R.id.textView_orderdate)
        TextView textOrderdate;



        @BindView(R.id.textview3)
        TextView textviewDispatch;
        @BindView(R.id.textView_dispatchtype)
        TextView textDispatchtype;


        @BindView(R.id.textview5)
        TextView textviewPayment;
        @BindView(R.id.textView_paymenttype)
        TextView textPaymenttype;



        @BindView(R.id.textview6)
        TextView textviewFoodDetails;


        @BindView(R.id.recyclerView_order_history_food_Details)
        RecyclerView recyclerViewFoodDetails;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
