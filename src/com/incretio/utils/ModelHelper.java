package com.incretio.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.incretio.models.AphorismVo;

public class ModelHelper {
	public static List<AphorismVo> formatForHtml(List<AphorismVo> aphorismList){
		List<AphorismVo> result = new ArrayList<>(aphorismList);
		result.forEach(new Consumer<AphorismVo>() {
			@Override
			public void accept(AphorismVo aphorismVo) {
				aphorismVo.setText(HtmlFormatter.formatParagraph(aphorismVo.getText()));				
			}
		});	
		return result;
	}
}
