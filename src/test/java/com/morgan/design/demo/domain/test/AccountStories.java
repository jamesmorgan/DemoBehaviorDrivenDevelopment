package com.morgan.design.demo.domain.test;

import java.util.List;

import org.jbehave.core.annotations.Configure;
import org.jbehave.core.annotations.UsingEmbedder;
import org.jbehave.core.annotations.UsingSteps;
import org.jbehave.core.annotations.spring.UsingSpring;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.spring.SpringAnnotatedEmbedderRunner;
import org.junit.runner.RunWith;

@RunWith(SpringAnnotatedEmbedderRunner.class)
@Configure
@UsingEmbedder(embedder = Embedder.class, generateViewAfterStories = true, ignoreFailureInStories = true, ignoreFailureInView = false, stepsFactory = true)
@UsingSpring(resources = "classpath:spring/test-config.xml")
@UsingSteps
public class AccountStories extends JUnitStories {

	@Override
	protected List<String> storyPaths() {
		List<String> findPaths = new StoryFinder().findPaths(CodeLocations.codeLocationFromPath("src/test/resources"),
				"com/morgan/design/demo/stories/*.story", "");
		for (String story : findPaths) {
			System.out.println("Found Story: " + story);
		}
		return findPaths;
	}

}
