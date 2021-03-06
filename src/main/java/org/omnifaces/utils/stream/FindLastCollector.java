/*
 * Copyright 2018 OmniFaces
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.omnifaces.utils.stream;

import static java.util.Collections.emptySet;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

class FindLastCollector<T> implements Collector<T, FindLastCollector.LastEncounteredElemement<T>, Optional<T>> {

	static class LastEncounteredElemement<T> {
		private boolean set;
		private T element;

		void nextElement(T nextElement) {
			set = true;
			element = nextElement;
		}

		LastEncounteredElemement<T> combine(LastEncounteredElemement<T> other) {
			if (other.set) {
				return other;
			}

			return this;
		}

		Optional<T> toOptional() {
			if (set) {
				return Optional.of(element);
			}

			return Optional.empty();
		}
	}

	@Override
	public Supplier<LastEncounteredElemement<T>> supplier() {
		return LastEncounteredElemement::new;
	}

	@Override
	public BiConsumer<LastEncounteredElemement<T>, T> accumulator() {
		return LastEncounteredElemement::nextElement;
	}

	@Override
	public BinaryOperator<LastEncounteredElemement<T>> combiner() {
		return LastEncounteredElemement::combine;
	}

	@Override
	public Function<LastEncounteredElemement<T>, Optional<T>> finisher() {
		return LastEncounteredElemement::toOptional;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return emptySet();
	}
}
