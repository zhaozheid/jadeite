package io.zzax.jadeite.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhe Zhao
 * @version $Revision: 1.0 $
 */
public class ViewFlow extends View {
	
	/*
	 * Mark - View - Life Cycle
	 */
	
	@Override
	protected void init() {
		super.init();
		this.setSize(800, 600);
		contentViews = new ArrayList<View>();
	}
	
	/*
	 * Mark - Flow Management - Properties
	 */
	
	private List<View> contentViews;
	
	/*
	 * Mark - Flow Management - Methods
	 */
	
	/**
	 * Method push.
	 * @param view View
	 */
	public void push(View view){
		if(contentViews.size() > 0){
			contentViews.get(contentViews.size() - 1).setVisible(false);
		}
		
		contentViews.add(view);
		view.setViewFlow(this);
		view.setSize(this.getSize());
		this.add(view);
		view.viewDidDisplay();
	}
	
	public void pop(){
		if (contentViews.size() > 0){
			View view = contentViews.get(contentViews.size() - 1);
			this.remove(view);
			contentViews.remove(view);
			contentViews.get(contentViews.size() - 1).setVisible(true);
		}
		if (contentViews.size() > 0){
			View view = contentViews.get(contentViews.size() - 1);
			view.viewDidDisplay();
		}
	}
}
