package com.doomdev.fybersampel;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;

/**
 * Created by and on 22.01.16.
 * Robolectric is looking for the AndroidManifest in a directory that no longer exists as of v1.4.
 * solution from : https://www.reddit.com/r/androiddev/comments/3oehn8/heres_how_to_make_robolectric_30_and_android/?
 */
public class CustomTestRunner extends RobolectricGradleTestRunner {
    public CustomTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        final String BUILD_PATH = "app/build/intermediates";
        final String flavor = BuildConfig.FLAVOR;
        final String type = BuildConfig.BUILD_TYPE;

        final FileFsFile assetsFile = FileFsFile.from(BUILD_PATH, config.assetDir(), flavor, type);
        final FileFsFile resFile = FileFsFile.from(BUILD_PATH, config.resourceDir(), "merged", flavor, type);
        final FileFsFile manifestFile = FileFsFile.from(
                BUILD_PATH, "manifests", "full", flavor, type, "AndroidManifest.xml"
        );

        return new AndroidManifest(manifestFile, resFile, assetsFile);
    }
}