
dev:
	@echo "Running figwheel for development."
	cd bdnc; npm install; lein figwheel

css:
	@echo "Building css."
	cd bdnc; npm run build:css

format:
	@echo "Formatting cljs."
	standard-clj fix bdnc/src/

