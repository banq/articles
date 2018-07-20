package org.jenkinsci.plugins.pengdemo;


import hudson.Extension;
import hudson.Launcher;
import hudson.model.*;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import java.io.IOException;

public class SleepBuilder extends Builder {

    private long time;

    @DataBoundConstructor
    public SleepBuilder(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener
            listener) throws InterruptedException, IOException {
        listener.getLogger().println("sleeping for " + time + " ms.");
        Thread.sleep(time);
        return true;
    }

    @Extension
    public static final class DescriptorImpl extends
            BuildStepDescriptor<Builder>{

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Sleep Builder";
        }

        public FormValidation doCheckTime(@QueryParameter String time){

            try {
                Long.valueOf(time);
                return FormValidation.ok();
            } catch (NumberFormatException e) {
                return FormValidation.error("Please enter a number");
            }
        }
    }



}


