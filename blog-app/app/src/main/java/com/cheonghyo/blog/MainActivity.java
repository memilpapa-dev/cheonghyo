package com.cheonghyo.blog;
import android.app.*;import android.os.*;import android.content.*;import android.webkit.*;import android.view.*;import android.widget.*;
public class MainActivity extends Activity {
 WebView web; TextView status;
 String title="곰탕 기름은 왜 여러 번 걷어낼까요?";
 String body="곰탕을 끓일 때 기름이 떠오르는 건 자연스러운 현상이에요. 청효 한우곰탕은 무쇠 가마솥에서 뼈 육수를 21시간 끓이면서 기름을 여러 번 걷어냅니다. 식힌 뒤에도 굳은 기름을 제거해요. 사골 40%, 꼬리 25%, 도가니 20%, 모둠뼈 15%를 사용하고 조미료·추출물·분말은 넣지 않습니다. 한 팩은 550g이며 양지 60g이 포함돼 있어요.";
 public void onCreate(Bundle b){super.onCreate(b); LinearLayout root=new LinearLayout(this);root.setOrientation(1);setContentView(root);
 Button home=new Button(this);home.setText("네이버 로그인 / 블로그 열기");root.addView(home);
 Button write=new Button(this);write.setText("1번 글쓰기 화면 열기");root.addView(write);
 Button fill=new Button(this);fill.setText("1번 글 자동 입력 시도");root.addView(fill);
 Button copy=new Button(this);copy.setText("제목·본문 복사");root.addView(copy);
 status=new TextView(this);status.setText("먼저 네이버에 로그인하세요. 발행은 직접 확인해 주세요.");root.addView(status);
 web=new WebView(this);root.addView(web,new LinearLayout.LayoutParams(-1,0,1));web.getSettings().setJavaScriptEnabled(true);web.getSettings().setDomStorageEnabled(true);
 web.setWebViewClient(new WebViewClient()); web.setWebChromeClient(new WebChromeClient());web.loadUrl("https://m.naver.com");
 home.setOnClickListener(v->web.loadUrl("https://m.blog.naver.com"));
 write.setOnClickListener(v->web.loadUrl("https://blog.naver.com/GoBlogWrite.naver"));
 copy.setOnClickListener(v->{((android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("청효 글",title+"\n\n"+body));status.setText("제목과 본문을 복사했어요");});
 fill.setOnClickListener(v->{String js="(function(){let es=[...document.querySelectorAll('input,textarea,[contenteditable=true]')].filter(e=>e.offsetParent!==null);let set=(e,s)=>{if(!e)return false;e.focus();if(e.isContentEditable){e.textContent=s;e.dispatchEvent(new InputEvent('input',{bubbles:true,data:s,inputType:'insertText'}));}else{let p=Object.getOwnPropertyDescriptor(e.tagName==='TEXTAREA'?HTMLTextAreaElement.prototype:HTMLInputElement.prototype,'value');p.set.call(e,s);e.dispatchEvent(new Event('input',{bubbles:true}));e.dispatchEvent(new Event('change',{bubbles:true}));}return true;};return set(es[0],"+org.json.JSONObject.quote(title)+")&&set(es[1],"+org.json.JSONObject.quote(body)+");})()";web.evaluateJavascript(js,r->status.setText("자동 입력 시도 결과: "+r+" / 화면에서 내용을 확인하세요"));});
 }
 public void onBackPressed(){if(web.canGoBack())web.goBack();else super.onBackPressed();}
}