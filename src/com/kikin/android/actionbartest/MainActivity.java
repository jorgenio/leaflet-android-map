package com.kikin.android.actionbartest;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends SherlockActivity {

	@SuppressLint("SetJavaScriptEnabled")
	private WebView myWebView;
	private String url ="http://widgets.ign.com/global/wikis/wikimap.html?full=true&controls=false&?popup=iphone";
	private String location="&map_id=Skyrim";
	private String filter="";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = new String("http://widgets.ign.com/global/wikis/wikimap.html?full=true&controls=false&?popup=iphone");
        setContentView(R.layout.activity_main);
        this.setTitle("Skyrim Android JSIO POC");
        // Obtain the webView by ID
        /*WebView*/myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // performance hacks!
        webSettings.setRenderPriority(RenderPriority.HIGH);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        
        // multi-touch zoom
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        //myWebView.loadUrl("http://widgets.ign.com/global/wikis/wikimap.html?full=true&?popup=iphone");
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient());
        
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.maps, menu);
		inflater.inflate(R.menu.filters, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    AlertDialog alert=null;
		
		// Handle item selection
	    switch (item.getItemId()) {
	    	case R.id.skyrim:
	    		location="&map_id=Skyrim";
	    		myWebView.loadUrl(this.url+location);
	    		return true;
	        case R.id.whiterun:
	        	location="&map_id=Whiterun";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.dawnstar:
	        	location="&map_id=Dawnstar";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.falkreath:
	        	location="&map_id=Falkreath";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.markarth:
	        	location="&map_id=Markarth";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.morthal:
	        	location="&map_id=Morthal";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.riften:
	        	location="&map_id=Riften";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.winterhold:
	        	location="&map_id=Winterhold";
	        	myWebView.loadUrl(this.url+location);
	            //finish();
	            return true;
	        case R.id.people:
	        	final CharSequence[] people = {"All People", "Followers", "Marriage Partners", "Trainers", "Guilds", "Cancel"};
	        	AlertDialog.Builder peopleBuilder = new AlertDialog.Builder(this);
	        	peopleBuilder.setTitle("Select a filter:");
	        	peopleBuilder.setItems(people, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(which){
						case 0: 
							filter="&categories=People";
							myWebView.loadUrl(url + location + filter); break;
						case 1: 							
							filter="&categories=Followers";
							myWebView.loadUrl(url + location + filter); break;
						case 2: 
							filter="&categories=MarriagePartners";
							myWebView.loadUrl(url + location + filter); break;
						case 3: 
							filter="&categories=Trainers";
							myWebView.loadUrl(url + location + filter); break;
						case 4: 
							filter="&categories=Guilds";
							myWebView.loadUrl(url + location + filter); break;
						default: dialog.cancel(); break;
						
						}
					}
				});
	        	alert = peopleBuilder.create();
	        	alert.show();
	        	return true;
	        case R.id.crafting:
	        	final CharSequence[] crafting = {"All Crafting", "Alchemy Tables", "Ore Veins", "Ingredients", "Cancel"};
	        	AlertDialog.Builder craftingBuilder = new AlertDialog.Builder(this);
	        	craftingBuilder.setTitle("Select a filter:");
	        	craftingBuilder.setItems(crafting, new DialogInterface.OnClickListener() {
	        	    public void onClick(DialogInterface dialog, int item) {
	        	        
	        	    	switch(item)
	        	    	{
	        	    	case 0:   
	        	    		filter="&categories=Crafting";
							myWebView.loadUrl(url + location + filter); break; //set filter for all crafting
	        	    	case 1: 
	        	    		filter="&categories=AlchemyTables";
							myWebView.loadUrl(url + location + filter); break;//set filter for alchemy tables
	        	    	case 2:   
	        	    		filter="&categories=OreVeins";
							myWebView.loadUrl(url + location + filter); break;//set filter for ore veins
	        	    	case 3:   
	        	    		filter="&categories=Ingredients";
							myWebView.loadUrl(url + location + filter); break;//set filter for ingredients
	        	    	default:  dialog.cancel(); break;
	        	    	}
	        	    	//our shit goes in here.
	        	    	Toast.makeText(getApplicationContext(), crafting[item], Toast.LENGTH_SHORT).show();
	        	    }
	        	});
	        	alert = craftingBuilder.create();
	        	alert.show();
	        	return true;
	        case R.id.dragons:
	        	final CharSequence[] dragons = {"All Dragons", "Dragon Shrines", "Dragon Priests", "Cancel"};
	        	AlertDialog.Builder dragonBuilder = new AlertDialog.Builder(this);
	        	dragonBuilder.setTitle("Select a filter:");
	        	dragonBuilder.setItems(dragons, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int item) {
						switch(item)
						{
							case 0: 
								filter="&categories=Dragons";
								myWebView.loadUrl(url + location + filter); break;
							case 1: 
								filter="&categories=DragonShrines";
								myWebView.loadUrl(url + location + filter); break;
							case 2: 
								filter="&categories=DragonPriests";
								myWebView.loadUrl(url + location + filter); break;
							default: dialog.cancel(); break;
						}						
					}
				});
	        	alert = dragonBuilder.create();
	        	alert.show();
	        	return true;
	        case R.id.items:
	        	final CharSequence[] items = {"All Items", "Unique Items", "Skill Books", "Treasure Maps", "Cancel"};
	        	AlertDialog.Builder itemBuilder = new AlertDialog.Builder(this);
	        	itemBuilder.setTitle("Select a filter:");
	        	itemBuilder.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int item) {
						switch(item)
						{
							case 0: 
								filter="&categories=Items";
								myWebView.loadUrl(url + location + filter); break;
							case 1: 
								filter="&categories=UniqueItems";
								myWebView.loadUrl(url + location + filter); break;
							case 2: 
								filter="&categories=SkillBooks";
								myWebView.loadUrl(url + location + filter); break;
							case 3:
								filter="&categories=TreasureMaps";
								myWebView.loadUrl(url + location + filter); break;
							default: dialog.cancel(); break;
						}						
					}
				});
	        	alert = itemBuilder.create();
	        	alert.show();
	        	return true;
	        case R.id.locations:
	        	final CharSequence[] locations = {"All Locations", "Camps", "Cities", "Jarl's Castles", "Settlements",
	        			"Farms", "Mills", "Forts","Shacks", "Stables", "Ports & Docks", "Ruins", "Caves", "Mines", "Passes",
	        			"Points of Interest", "Ponds & Lakes", "Tombs", "Towers & Watchtowers", "Lighthouses", "Groves", "Other Locations",
	        			"Cancel"};
	        	AlertDialog.Builder locationBuilder = new AlertDialog.Builder(this);
	        	locationBuilder.setTitle("Select a filter:");
	        	locationBuilder.setItems(locations, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int item) {
						switch(item)
						{
							case 0: 
								filter="&categories=CitiesTownCavesEtc";
								myWebView.loadUrl(url + location + filter); break;
							case 1: 
								filter="&categories=Camps";
								myWebView.loadUrl(url + location + filter); break;
							case 2: 
								filter="&categories=Cities";
								myWebView.loadUrl(url + location + filter); break;
							case 3:
								filter="&categories=JarlsCastles";
								myWebView.loadUrl(url + location + filter); break;
							case 4:
								filter="&categories=Settlements";
								myWebView.loadUrl(url + location + filter); break;
							case 5:
								filter="&categories=Farms";
								myWebView.loadUrl(url + location + filter); break;
							case 6:
								filter="&categories=Mills";
								myWebView.loadUrl(url + location + filter); break;
							case 7: 
								filter="&categories=Forts";
								myWebView.loadUrl(url + location + filter); break;
							case 8: 
								filter="&categories=Shacks";
								myWebView.loadUrl(url + location + filter); break;
							case 9: 
								filter="&categories=Stables";
								myWebView.loadUrl(url + location + filter); break;
							case 10: 
								filter="&categories=PortsandDocks";
								myWebView.loadUrl(url + location +  filter); break;
							case 11:
								filter="&categories=Ruins";
								myWebView.loadUrl(url + location + filter); break;
							case 12: 
								filter="&categories=Caves";
								myWebView.loadUrl(url + location + filter); break;
							case 13:
								filter="&categories=Mines";
								myWebView.loadUrl(url + location + filter); break;
							case 14:
								filter="&categories=Passes";
								myWebView.loadUrl(url + location + filter); break;
							case 15:
								filter="&categories=PointsofInterest";
								myWebView.loadUrl(url + location + filter); break;
							case 16: 
								filter="&categories=PondsandLakes";
								myWebView.loadUrl(url + location + filter); break;
							case 17:
								filter="&categories=Tombs";
								myWebView.loadUrl(url + location + filter); break;
							case 18:
								filter="&categories=TowersandWatchtowers";
								myWebView.loadUrl(url + location + filter); break;
							case 19:
								filter="&categories=Lighthouses";
								myWebView.loadUrl(url + location + filter); break;
							case 20:
								filter="&categories=Groves";
								myWebView.loadUrl(url + location + filter); break;
							case 21:
								filter="&categories=OtherLocations";
								myWebView.loadUrl(url + location + filter); break;
							default: dialog.cancel(); break;
						}						
					}
				});
	        	alert = locationBuilder.create();
	        	alert.show();
	        	return true;
	        case R.id.standingstones:
	        	filter="&categories=StandingStones";
	        	myWebView.loadUrl(url+filter);
	            //finish();
	            return true;
	        case R.id.wordwalls:
	        	filter="&categories=WordWalls";
	        	myWebView.loadUrl(url+filter);
	            //finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
