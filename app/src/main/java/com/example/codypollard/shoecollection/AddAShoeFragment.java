package com.example.codypollard.shoecollection;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.codypollard.shoecollection.JavaBeans.Shoe;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddAShoeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddAShoeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAShoeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentManager fm;
    LinearLayout seePic;
    private String currentPhotoPath;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int CAMERA_PERMISSION_LABEL = 2;


    private OnFragmentInteractionListener mListener;

    public AddAShoeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddAShoeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddAShoeFragment newInstance(String param1, String param2) {
        AddAShoeFragment fragment = new AddAShoeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_ashoe, container, false);
        MainActivity.fab.hide();
        final EditText brands = view.findViewById(R.id.brandEdit);
        final EditText type = view.findViewById(R.id.typeEdit);
        final EditText name = view.findViewById(R.id.nameEdit);
        final EditText description = view.findViewById(R.id.descriptionEdit);
        final EditText colourway = view.findViewById(R.id.colourwayEdit);
        final EditText condition = view.findViewById(R.id.conditionEdit);
        final EditText retailPrice = view.findViewById(R.id.retailEdit);
        final ImageView picture = view.findViewById(R.id.cameraButton);
        seePic =  (LinearLayout) view.findViewById(R.id.seePic);
        Button createButton = view.findViewById(R.id.createButton);

        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED)) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        final AlertDialog alertDialog =
                                new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("Can we use your camera?");
                        alertDialog.setMessage("We need access to read and write to external storage to use the camera");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CONFIRM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_LABEL);
                            }
                        });
                        alertDialog.show();
                    } else {

                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_PERMISSION_LABEL);
                    }
                }
                else {
                    File pictureFile = null;
                    try {
                        pictureFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(),
                            "com.example.codypollard.shoecollection.FileProvider", pictureFile));
                    if (takePicIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }

            }
        });
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shoe shoe = new Shoe(
                        name.getText().toString(),
                        description.getText().toString(),
                        brands.getText().toString(),
                        type.getText().toString(),
                        colourway.getText().toString(),
                        condition.getText().toString(),
                        retailPrice.getText().toString(),
                        currentPhotoPath
                );
                //Get access to the database
                DatabaseHandler db = new DatabaseHandler(getContext());
                //Call the addShoe function
                //Populates the db with the info from the form
                db.addShoe(shoe);
                db.close();
                fm = getActivity().getSupportFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = BitmapFactory.decodeFile(currentPhotoPath);
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = new ImageView(getContext());
            imageView.setImageBitmap(image);
            seePic.addView(imageView);
        }
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
