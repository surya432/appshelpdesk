package com.surya432.apis.Activity.Sales.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.surya432.apis.Activity.Sales.Model.ModelSpinner;

import java.util.List;

public class DDLComplainModel extends ArrayAdapter<ModelSpinner> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<ModelSpinner> values;

    public DDLComplainModel(Context context, int resource, List<ModelSpinner> objects) {
        super(context, resource, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public ModelSpinner getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(values.get(position).getName());

        return label;
    }

    public ModelSpinner getSelectedItem(int position) {
        return values.get(position);
    }
    public String getSelectedItem2(int keyword) {
        String selected_Id = values.get(keyword).getId();
        return selected_Id;
    }
}
