# MLand

# AIDE branch

## Java
##### [MLand.java](https://goo.gl/tGKKOj)
##### [MLandActivity.java](https://goo.gl/C63Ubx)

## Layout
##### [mland.xml](https://goo.gl/qAlCXv)
##### [mland_scorefield.xml](https://goo.gl/uV4UQs)

## Values
##### [mland_config.xml](https://goo.gl/wCxeZf)
##### [mland_strings.xml](https://goo.gl/GsFhzO)

## Drawables
##### For ease-of-purpose, I will link you to this [drawable-nodpi](https://goo.gl/A8qrMm) folder because that basically holds all of the used drawable xml files.

## AndroidManifest.xml entry:
##### [AndroidManifest.xml](https://goo.gl/GKBdnS)
        <activity android:name=".egg.MLandActivity"
                  android:theme="@android:style/Theme.Material.NoActionBar"
                  android:exported="true"
                  android:icon="@drawable/icon"
                  android:label="@string/mland"
                  android:hardwareAccelerated="true"
                  android:launchMode="singleInstance"
                  android:screenOrientation="locked"
                  android:process=":sweetsweetdesserts"
                  android:excludeFromRecents="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="com.android.internal.category.PLATLOGO" />
            </intent-filter>
        </activity>

## Required permissions:
    <uses-permission android:name="android.permission.VIBRATE" />
