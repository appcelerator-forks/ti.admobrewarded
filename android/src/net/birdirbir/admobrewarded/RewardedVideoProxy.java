package net.birdirbir.admobrewarded;

import android.app.Activity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import java.util.HashMap;

@Kroll.proxy
public class RewardedVideoProxy extends KrollProxy {

    String adUnitId;
    RewardHelper rewardHelper;

    RewardedVideoProxy(String adUnitId) {
        this.adUnitId = adUnitId;
        rewardHelper = new RewardHelper(getActivity(),adUnitId);
    }


    @Kroll.method
    public void loadAd(KrollDict obj) {
        KrollFunction onRewardedAdLoaded = obj.containsKey("onRewardedAdLoaded") ? (KrollFunction)obj.get("onRewardedAdLoaded") : null;
        KrollFunction onRewardedAdFailedToLoad = obj.containsKey("onRewardedAdFailedToLoad") ? (KrollFunction)obj.get("onRewardedAdFailedToLoad") : null;


        this.rewardHelper.createAndLoadRewardedAd(new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                if(onRewardedAdLoaded != null){
                    KrollDict data = new KrollDict();
                    data.put("", "");
                    onRewardedAdLoaded.callAsync(getKrollObject(), data);
                }
            }

            @Override
            public void onRewardedAdFailedToLoad(int errorCode) {
                if(onRewardedAdFailedToLoad != null){
                    KrollDict data = new KrollDict();
                    data.put("errorCode", errorCode);
                    onRewardedAdFailedToLoad.callAsync(getKrollObject(), data);
                }
            }
        });
    }

    @Kroll.method
    public void showAd(KrollDict obj) {


        KrollFunction onRewardedAdOpened = obj.containsKey("onRewardedAdOpened") ? (KrollFunction)obj.get("onRewardedAdOpened") : null;
        KrollFunction onRewardedAdClosed = obj.containsKey("onRewardedAdClosed") ? (KrollFunction)obj.get("onRewardedAdClosed") : null;
        KrollFunction onUserEarnedReward = obj.containsKey("onUserEarnedReward") ? (KrollFunction)obj.get("onUserEarnedReward") : null;
        KrollFunction onRewardedAdFailedToShow = obj.containsKey("onRewardedAdFailedToShow") ? (KrollFunction)obj.get("onRewardedAdFailedToShow") : null;
        Boolean forceLoad = obj.containsKey("forceLoad") ? obj.getBoolean("forceLoad") : false;



        this.rewardHelper.showAd(new RewardedAdCallback() {
            @Override
            public void onRewardedAdOpened() {
                if(onRewardedAdOpened != null){
                    KrollDict data = new KrollDict();
                    data.put("", "");
                    onRewardedAdOpened.callAsync(getKrollObject(), data);
                }
            }

            @Override
            public void onRewardedAdClosed() {
                if(onRewardedAdClosed != null){
                    KrollDict data = new KrollDict();
                    data.put("", "");
                    onRewardedAdClosed.callAsync(getKrollObject(), data);
                }
            }

            @Override
            public void onUserEarnedReward(RewardItem reward) {
                if(onUserEarnedReward != null){
                    KrollDict data = new KrollDict();
                    data.put("", "");
                    onUserEarnedReward.callAsync(getKrollObject(), data);
                }
            }

            @Override
            public void onRewardedAdFailedToShow(int errorCode) {
                if (onRewardedAdFailedToShow != null){
                    KrollDict data = new KrollDict();
                    data.put("", "");
                    onRewardedAdFailedToShow.callAsync(getKrollObject(), data);
                }
            }
        });
    }


}


class RewardHelper {

    RewardedAd reward;
    Activity activity;
    String adUnitId;

    public RewardHelper(Activity activity, String adUnitId) {
        this.activity = activity;
        this.adUnitId = adUnitId;
    }

    public void createAndLoadRewardedAd(RewardedAdLoadCallback rewardedAdLoadCallback) {
        reward = new RewardedAd(activity, adUnitId);
        reward.loadAd(new AdRequest.Builder().build(), rewardedAdLoadCallback);
    }


    public void showAd(RewardedAdCallback rewardedAdCallback) {
        if (reward != null && reward.isLoaded()) {
            reward.show(activity, rewardedAdCallback);
        }

    }

}
