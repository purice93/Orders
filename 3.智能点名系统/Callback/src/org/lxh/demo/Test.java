package org.lxh.demo;

import com.iflytek.speech.SpeechError;
import com.iflytek.speech.SynthesizerPlayer;
import com.iflytek.speech.SynthesizerPlayerListener;
import com.iflytek.util.Version;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sname="��Һ�";

		if (SynthesizerPlayer.getSynthesizerPlayer() == null)
			SynthesizerPlayer.createSynthesizerPlayer("appid="
					+ Version.getAppid());
		SynthesizerPlayer synthesizer = SynthesizerPlayer.getSynthesizerPlayer();
		
		 synthesizer.setVoiceName("xiaoyu");
 			// �����ʶ��ٶ�Ϊ50
 			synthesizer.setSpeed(50);
 			// �ϳ��ı�ΪTEXT_CONTENT�ľ��ӣ����ü�����ΪmSynListener
 			synthesizer.playText(sname.trim(), null,
 					mSynListener);
 			System.out.println(sname);
	}
	private static SynthesizerPlayerListener mSynListener = new SynthesizerPlayerListener() {

		 
		public void onEnd(SpeechError error) {
		}

	 
		public void onBufferPercent(int percent, int beginPos, int endPos,
				String args) {

		}

		public void onPlayBegin() {

		}

		public void onPlayPaused() {

		}

		public void onPlayPercent(int percent, int beginPos, int endPos) {
		}

		public void onPlayResumed() {

		}
	};
}

       



