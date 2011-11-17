package com.andrios.fleetknowledge.Adapters;

import java.util.ArrayList;

import com.andrios.fleetknowledge.R;
import com.andrios.fleetknowledge.Models.Ship;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableShipAdapter extends BaseExpandableListAdapter {

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    private Context context;
    private ArrayList<String> groups;
    private ArrayList<ArrayList<Ship>> children;
    
    public ExpandableShipAdapter(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<Ship>> children) {
        this.context = context;
        this.groups = groups;
        this.children = children;
    }

    /**
     * A general add method, that allows you to add a Vehicle to this list
     * 
     * Depending on if the category opf the vehicle is present or not,
     * the corresponding item will either be added to an existing group if it 
     * exists, else the group will be created and then the item will be added
     * @param vehicle
     */
    public void addItem(Ship s) {
        if (!groups.contains(s.getType())) {
            groups.add(s.getType());
        }
        int index = groups.indexOf(s.getType());
        if (children.size() < index + 1) {
            children.add(new ArrayList<Ship>());
        }
        children.get(index).add(s);
        sort(children.get(index));
    }
    
    public Object removeItem(int groupPosition, int childPosition){
    	
    	return children.get(groupPosition).remove(childPosition);
    }

    public Object getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    // Return a child view. You can load your custom layout here.
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
        Ship s = (Ship) getChild(groupPosition, childPosition);
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item_ship, null);
        }
            
      
            if (s != null) {
                    TextView titleTXT = (TextView) v.findViewById(R.id.ship_list_item_classLBL);
                    ImageView ribbonIMG = (ImageView) v.findViewById(R.id.ship_list_item_IMG);
                    
                    if (titleTXT != null) {
                          titleTXT.setText(s.getShip_class());                         
                    }
                    
                   if(ribbonIMG != null){
                    	ribbonIMG.setImageResource(s.getImage());
                   }
                    
                    
                    
            }
            return v;
    }

    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Return a group view. You can load your custom layout here.
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = infalInflater.inflate(R.layout.group_layout, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
        tv.setText(group);
      //  ImageView iv = (ImageView) convertView.findViewById(R.id.group_layout_img);
        if(group.equals("Army")){
        	//iv.setImageResource(R.drawable.crest_army);
        }else if(group.equals("Navy")){
        	//iv.setImageResource(R.drawable.crest_navy);
        }else if(group.equals("Marine Corps")){
        	//iv.setImageResource(R.drawable.crest_marines);
        }else if(group.equals("Air Force")){
        	//iv.setImageResource(R.drawable.crest_air_force);
        }else if(group.equals("Coast Guard")){
        	//iv.setImageResource(R.drawable.crest_coast_guard);
        }
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
    
    private void sort(ArrayList<Ship> a){
    
    	//Collections.sort(a, new Comparator<Ship>() {

			//public int compare(Ship object1, Ship object2) {
				//if(object1.getPrecedence() > object2.getPrecedence()){
				//	return 1;
				//}else if(object1.getPrecedence() == object2.getPrecedence()){
				//	return 0;
				//}else{
				//	return -1;
				//}
				
			//}

					

				//});
    }
    


}
