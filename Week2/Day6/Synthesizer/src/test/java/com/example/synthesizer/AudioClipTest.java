package com.example.synthesizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AudioClipTest {
    // 创建 AudioClip 对象
    private AudioClip audioClip;

    @BeforeEach
    public void setUp() {
        audioClip = new AudioClip();
    }

    @Test
    public void testSetAndGetPositiveSample() {
        audioClip.setSample(0, 100);
        int sample = audioClip.getSample(0);
        assertEquals(100, sample);
    }

    @Test
    public void testSetAndGetNegativeSample() {
        audioClip.setSample(0, -50);
        int sample = audioClip.getSample(0);
        assertEquals(-50, sample);
    }

    @Test
    public void testSetAndGetMaxShortValue() {
        audioClip.setSample(0, Short.MAX_VALUE);
        int sample = audioClip.getSample(0);
        assertEquals(Short.MAX_VALUE, sample);
    }

    @Test
    public void testSetAndGetMinShortValue() {
        audioClip.setSample(0, Short.MIN_VALUE);
        int sample = audioClip.getSample(0);
        assertEquals(Short.MIN_VALUE, sample);
    }

    @Test
    public void testGetData() {
        byte[] data = audioClip.getData();
        assertNotNull(data);
        assertEquals(2 * AudioClip.DURATION * AudioClip.SAMPLE_RATE, data.length);
    }
    @org.junit.Test
    public void testSetAndGetRandomSamples() {
        AudioClip audioClip = new AudioClip(); // 创建一个 AudioClip 对象
        Random random = new Random(); // 创建一个随机数生成器

        // 设置随机样本值
        for (int i = 0; i < audioClip.getData().length; i++) {
            short randomValue = (short) random.nextInt(Short.MAX_VALUE + 1);
            audioClip.setSample(i, randomValue);
        }

        // 检查随机样本值是否正确
        for (int i = 0; i < audioClip.getData().length; i++) {
            short expectedValue = (short) audioClip.getSample(i);
            assertEquals(expectedValue, audioClip.getSample(i));
        }
    }
}