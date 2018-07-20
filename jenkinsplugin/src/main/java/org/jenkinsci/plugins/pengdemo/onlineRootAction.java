package org.jenkinsci.plugins.pengdemo;

import hudson.Extension;
import hudson.model.RootAction;

@Extension
public class onlineRootAction implements RootAction {
    @Override
    public String getIconFileName() {
        return "clipboard.png";
    }

    @Override
    public String getDisplayName() {
        return "online demo url";
    }

    @Override
    public String getUrlName() {
        return "http://www.baidu.com";
    }
}
