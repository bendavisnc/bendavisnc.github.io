
dev: clean
	@echo "Running figwheel for development."
	cd bdnc; npm install; lein fig -- -b dev -r

css:
	@echo "Building css."
	cd bdnc; npm run build:css

format:
	@echo "Formatting cljs."
	standard-clj fix bdnc/src/

commit:
	@echo "Initiating dev commit."
	git add -u **/*.cljs **/*.css; git commit

clean:
	@echo "Removing target directories."
	cd bdnc; rm -rf ./target ./node_modules

buildonce:
	@echo "Building javascript."
	cd bdnc; npm install; lein fig -- --build-once prod

rollout: buildonce
	@echo "Preparing static site."
	rm -r bdncout
	mkdir bdncout
	cp -r bdnc/resources/public/ bdncout
	mkdir bdncout/public/js
	cp bdnc/target/public/cljs-out/prod/main_bundle.js bdncout/public/js/
	mv bdncout/public/index_prod.html bdncout/public/index.html
