package hsm.yeng.syllabus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hsm.yeng.R;
import hsm.yeng.util.Util;


public class ModuleSixFragment extends Fragment {
    ListView mRecyclerView;
    TextView mModuleNum,mModule_pct,mModules;
    Util util;
    public ModuleSixFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.modulesview, container, false);
        mRecyclerView= (ListView) view.findViewById(R.id.listview_module);
        mModuleNum= (TextView) view.findViewById(R.id.modile_num);
        mModule_pct= (TextView) view.findViewById(R.id.module_percentage);
        mModules= (TextView) view.findViewById(R.id.modules);
        util=new Util();
        mModuleNum.setText("Module 6 ");
        JSONObject object= null;
        String modules_content="";
        try {
            ArrayList<syllabusDatamodel> arrayList=new ArrayList<>();
            object = new JSONObject(util.loadJSONFromAsset(getActivity(),"ktusyllabusbtech.json"));
            JSONArray syllabus=object.getJSONArray("syllabus");
            ArrayList<String> arrayList1=new ArrayList<>();
            JSONObject jsonObject1=syllabus.getJSONObject(0);
            JSONArray modules=jsonObject1.getJSONArray("modules");
            JSONObject module=modules.getJSONObject(5);
            mModule_pct.setText(module.optString("mark percentage")+"%");
            JSONArray contents=module.getJSONArray("contents");
            for (int i=0;i<contents.length();i++){
                JSONObject content=contents.getJSONObject(i);
                Log.e("module","content"+content.optString("content"));
                modules_content=modules_content+content.optString("content")+"\n\n";
                arrayList1.add(content.optString("content"));

            }
            mModules.setText(modules_content);
            ArrayAdapter adapter=new ArrayAdapter(getActivity(),R.layout.custometectview,arrayList1);
            mRecyclerView.setAdapter(adapter);
            Log.e("modules","success"+arrayList.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }



        // Inflate the layout for this fragment
        return view;
    }

}
