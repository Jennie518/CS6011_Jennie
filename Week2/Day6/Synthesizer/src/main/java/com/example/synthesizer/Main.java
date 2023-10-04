package com.example.synthesizer;

import javax.sound.sampled.*;

public class Main {
    public static void main(String[] args) {
        try {
            Clip c = AudioSystem.getClip();
            AudioFormat format16 = new AudioFormat(44100, 16, 1, true, false);

            // 创建第一个正弦波和 Volume Adjuster
            AudioComponent sineWave1 = new SineWave(440);
            AudioComponent quieterSineWave1 = new VolumeAdjuster(sineWave1, 0.5);

            // 创建第二个正弦波和 Volume Adjuster
            AudioComponent sineWave2 = new SineWave(920);
            AudioComponent quieterSineWave2 = new VolumeAdjuster(sineWave2, 0.3);

            // 创建 Mixer，并将两个调整过音量的正弦波连接到 Mixer
            Mixer mixer = new Mixer(2);
            mixer.connectInput(quieterSineWave1, 0);
            mixer.connectInput(quieterSineWave2, 1);

//            // 获取混合后的音频剪辑
            AudioClip mixedAudio = mixer.getClip();
//            c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);
            c.open(format16, mixedAudio.getData(), 0, mixedAudio.getData().length);
            System.out.println("About to play...");
            c.start();
            c.loop(2);

            while (c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning()) {
                // 等待音频播放完成
            }
            System.out.println("Done.");
            c.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}


//package com.example.synthesizer;
//import javax.sound.sampled.*;
//public class Main {
//    // Get properties from the system about samples rates, etc.
//// AudioSystem is a class from the Java standard library.
//    public static void main(String[] args){
//        try{
//            Clip c = AudioSystem.getClip(); // Note, this is different from our AudioClip class.
//
//            // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
//            AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );
//
//            AudioComponent gen = new SineWave(220); // Your code
//            AudioClip clip = gen.getClip();         // Your code
//
//            c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.
//
//            System.out.println( "About to play..." );
//            c.start(); // Plays it.
//            c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total
//
//            // Makes sure the program doesn't quit before the sound plays.
//            while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
//                // Do nothing while we wait for the note to play.
//            }
//            System.out.println( "Done." );
//            c.close();
//        }catch (LineUnavailableException e){
//            e.printStackTrace();
//        }
//    }
//
//}