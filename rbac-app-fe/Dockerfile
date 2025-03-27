# Stage 1: Build the Next.js application
FROM node:18-alpine AS builder
WORKDIR /app
# Copy package files and install dependencies
COPY package.json package-lock.json ./
RUN npm install
# Copy the rest of the application code
COPY . .
# Build the Next.js application for production
RUN npm run build

# Stage 2: Run the production application
FROM node:18-alpine AS runner
WORKDIR /app
ENV NODE_ENV=production
# Copy built files from the builder stage
COPY --from=builder /app/next.config.js ./
COPY --from=builder /app/public ./public
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/package.json ./
# Expose the default port (Next.js typically runs on 3000)
EXPOSE 3000
CMD ["npm", "start"]
